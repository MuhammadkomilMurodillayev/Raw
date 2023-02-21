package uz.olmossoft.raw.mapper;

import org.springframework.stereotype.Component;
import uz.olmossoft.raw.domain.Advertisement;
import uz.olmossoft.raw.domain.AdvertisementPhoto;
import uz.olmossoft.raw.dto.AdvertisementCreateDTO;
import uz.olmossoft.raw.dto.AdvertisementDTO;
import uz.olmossoft.raw.dto.AdvertisementPhotoDTO;
import uz.olmossoft.raw.dto.AdvertisementUpdateDTO;
import uz.olmossoft.raw.enums.ModStatus;
import uz.olmossoft.raw.enums.Status;
import uz.olmossoft.raw.repository.AdvertisementPhotoRepository;
import uz.olmossoft.raw.service.AdvertisementPhotoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammadkomil Murodillayev, ср 17:34. 1/11/23
 */

@Component
public class AdvertisementMapper {

    private final AdvertisementPhotoRepository advertisementPhotoRepository;
    private final AdvertisementPhotoService advertisementPhotoService;

    public AdvertisementMapper(AdvertisementPhotoRepository advertisementPhotoRepository, AdvertisementPhotoService advertisementPhotoService) {
        this.advertisementPhotoRepository = advertisementPhotoRepository;
        this.advertisementPhotoService = advertisementPhotoService;
    }

    public AdvertisementDTO toDTO(Advertisement advertisement) {

        AdvertisementPhoto advertisementPhoto = advertisementPhotoRepository.findByAdvertisementId(advertisement.getId());

        AdvertisementPhotoDTO photo = AdvertisementPhotoDTO.builder()
                .id(advertisementPhoto.getId())
                .generatedName(advertisementPhoto.getGeneratedName())
                .build();

        return AdvertisementDTO.builder()
                .id(advertisement.getId())
                .photo(photo)
                .description(advertisement.getDescription())
                .price(advertisement.getPrice())
                .districtId(advertisement.getDistrictId())
                .modStatus(advertisement.getModStatus())
                .name(advertisement.getName())
                .status(advertisement.getStatus())
                .regionId(advertisement.getRegionId())
                .volume(advertisement.getVolume())
                .currency(advertisement.getCurrency())
                .phone(advertisement.getPhone())
                .build();

    }

    public List<AdvertisementDTO> toDTO(List<Advertisement> advertisements) {

        List<AdvertisementDTO> advertisementList = new ArrayList<>();

        advertisements.forEach(advertisement -> {
            advertisementList.add(toDTO(advertisement));
        });

        return advertisementList;
    }

    public Advertisement fromDTO(AdvertisementCreateDTO dto) {
        return Advertisement.builder()
                .name(dto.getName())
                .status(Status.ACTIVE)
                .description(dto.getDescription())
                .districtId(dto.getDistrictId())
                .modStatus(ModStatus.IN_PROCESS)
                .phone(dto.getPhone())
                .price(dto.getPrice())
                .regionId(dto.getRegionId())
                .volume(dto.getVolume())
                .currency(dto.getCurrency())
                .build();
    }

    public Advertisement fromUpdateDTO(Advertisement advertisement, AdvertisementUpdateDTO dto) {

        AdvertisementPhoto advertisementPhoto = advertisementPhotoRepository.findByAdvertisementId(advertisement.getId());
        advertisementPhotoRepository.delete(advertisementPhoto);
        advertisementPhotoService.uploadFile(dto.getPhoto(), advertisement.getId());

        advertisement.setCurrency(dto.getCurrency());
        advertisement.setDescription(dto.getDescription());
        advertisement.setName(dto.getName());
        advertisement.setPhone(dto.getPhone());
        advertisement.setPrice(dto.getPrice());
        advertisement.setRegionId(dto.getRegionId());
        advertisement.setDistrictId(dto.getDistrictId());
        advertisement.setId(dto.getId());
        advertisement.setVolume(dto.getVolume());

        return advertisement;
    }


}
