package uz.olmossoft.raw.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.olmossoft.raw.domain.AdvertisementPhoto;
import uz.olmossoft.raw.property.ServerProperty;
import uz.olmossoft.raw.repository.AdvertisementPhotoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * @author Muhammadkomil Murodillayev, ср 18:31. 1/11/23
 */

@Service
public class AdvertisementPhotoService {

    private final ServerProperty serverProperty;

    private final AdvertisementPhotoRepository repository;

    public AdvertisementPhotoService(ServerProperty serverProperty, AdvertisementPhotoRepository repository) {
        this.serverProperty = serverProperty;
        this.repository = repository;
    }

    public void uploadMultiFile(List<MultipartFile> photos, Long advertisementId) {
        photos.forEach(multipartFile -> {

            uploadFile(multipartFile, advertisementId);

        });
    }

    public void uploadFile(MultipartFile photo, Long advertisementId) {

        String extension = StringUtils.getFilenameExtension(photo.getOriginalFilename());

        String fileGeneratedName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
        String photoPath = serverProperty.getPhotoStoragePath() + fileGeneratedName;

        Path path = Paths.get(photoPath);

        try {
            Files.copy(photo.getInputStream(), path);

        } catch (IOException e) {

            e.printStackTrace();
        }

        AdvertisementPhoto advertisementPhoto = new AdvertisementPhoto();

        advertisementPhoto.setContentType(photo.getContentType());
        advertisementPhoto.setExtension(extension);
        advertisementPhoto.setName(photo.getName());
        advertisementPhoto.setGeneratedName(fileGeneratedName);
        advertisementPhoto.setSize(photo.getSize());
        advertisementPhoto.setAdvertisementId(advertisementId);

        repository.save(advertisementPhoto);

    }
}
