package uz.olmossoft.raw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.olmossoft.raw.domain.AdvertisementPhoto;

import java.util.List;

/**
 * @author Muhammadkomil Murodillayev, ср 20:25. 1/11/23
 */
@Repository
public interface AdvertisementPhotoRepository extends JpaRepository<AdvertisementPhoto, Long> {

    List<AdvertisementPhoto> findAllByAdvertisementId(Long id);

    AdvertisementPhoto findByAdvertisementId(Long id);
}
