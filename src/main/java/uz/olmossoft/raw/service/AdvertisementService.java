package uz.olmossoft.raw.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.webjars.NotFoundException;
import uz.olmossoft.raw.domain.Advertisement;
import uz.olmossoft.raw.dto.*;
import uz.olmossoft.raw.enums.ModStatus;
import uz.olmossoft.raw.enums.Status;
import uz.olmossoft.raw.mapper.AdvertisementMapper;
import uz.olmossoft.raw.property.ServerProperty;
import uz.olmossoft.raw.repository.AdvertisementRepository;

import java.io.*;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

/**
 * @author Muhammadkomil Murodillayev, ср 15:26. 1/11/23
 */

@Service
public class AdvertisementService {

    private final AdvertisementMapper mapper;

    private final AdvertisementPhotoService advertisementPhotoService;
    private final ServerProperty serverProperty;
    private final AdvertisementRepository repository;

    public AdvertisementService(AdvertisementMapper mapper, AdvertisementPhotoService advertisementPhotoService,
                                ServerProperty serverProperty, AdvertisementRepository repository) {
        this.mapper = mapper;
        this.advertisementPhotoService = advertisementPhotoService;
        this.serverProperty = serverProperty;
        this.repository = repository;
    }

    public ResponseEntity<DataDTO<Long>> create(AdvertisementCreateDTO dto) {

        Advertisement advertisement = mapper.fromDTO(dto);

        Long advertisementId = repository.save(advertisement).getId();

        advertisementPhotoService.uploadFile(dto.getPhoto(), advertisementId);

        return new ResponseEntity<>(new DataDTO<>(advertisementId), HttpStatus.OK);
    }

    public ResponseEntity<DataDTO<AdvertisementDTO>> get(Long id) {

        Advertisement advertisement;

        Optional<Advertisement> advertisementOptional = repository.findById(id);

        if (advertisementOptional.isPresent()) {
            advertisement = advertisementOptional.get();
        } else throw new NotFoundException("Advertisement not found");

        AdvertisementDTO advertisementDTO = mapper.toDTO(advertisement);

        return new ResponseEntity<>(new DataDTO<>(advertisementDTO), HttpStatus.OK);
    }

    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("orderTime").descending());
        Page<Advertisement> advertisements = repository.findAllByStatusAndModStatus(Status.ACTIVE, ModStatus.CONFIRMED, pageable);
        List<AdvertisementDTO> advertisementDTOList = mapper.toDTO(advertisements.get().toList());

        return new ResponseEntity<>(new DataDTO<>(advertisementDTOList, advertisements.getTotalPages()), HttpStatus.OK);

    }

    public ResponseEntity<DataDTO<Long>> update(AdvertisementUpdateDTO dto) {

        Advertisement updateAdvertisement;

        Optional<Advertisement> advertisementOptional = repository.findById(dto.getId());

        if (advertisementOptional.isPresent()) {
            updateAdvertisement = mapper.fromUpdateDTO(advertisementOptional.get(), dto);
            repository.save(updateAdvertisement);

            return new ResponseEntity<>(new DataDTO<>(updateAdvertisement.getId()), HttpStatus.OK);

        }

        throw new NotFoundException("failed updated");

    }


    public ResponseEntity<DataDTO<Long>> delete(Long id) {
        Advertisement advertisement;

        Optional<Advertisement> optionalAdvertisement = repository.findById(id);
        if (optionalAdvertisement.isPresent()) {
            advertisement = optionalAdvertisement.get();
            advertisement.setDeleted(true);
            repository.save(advertisement);

            return new ResponseEntity<>(new DataDTO<>(advertisement.getId()), HttpStatus.OK);
        }

        throw new NotFoundException("failed deleted");
    }

    public void downloadPhoto(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        File file = new File(serverProperty.getPhotoStoragePath() + fileName);

        if (file.exists()) {

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());

            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);

            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }

    public void setStatus(Long id, Status status) {

        Optional<Advertisement> advertisementOptional = repository.findById(id);

        advertisementOptional.ifPresentOrElse(advertisement -> {
            advertisement.setStatus(status);
            repository.save(advertisement);
        }, () -> {
            throw new NotFoundException("failed set status");
        });


    }

    public void setModStatus(Long id, ModStatus status) {
        Optional<Advertisement> advertisementOptional = repository.findById(id);

        advertisementOptional.ifPresentOrElse(advertisement -> {
            advertisement.setModStatus(status);
            repository.save(advertisement);
        }, () -> {
            throw new NotFoundException("failed set moderator status");
        });
    }

    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllByFilter(AdvertisementFilterDTO filterDTO) {

//        List<Advertisement> advertisements = repository.findAllByFilter(filterDTO);
        return null;
    }
}
