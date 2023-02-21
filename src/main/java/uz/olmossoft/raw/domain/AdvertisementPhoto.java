package uz.olmossoft.raw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

/**
 * @author Muhammadkomil Murodillayev, вт 22:27. 1/10/23
 */

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted = false")
public class AdvertisementPhoto extends Auditable {

    private String generatedName;

    private String name;

    @Column(name = "content_type")
    private String contentType;

    private String extension;

    private String path;

    private Long size;

    @Column(name = "advertisement_id")
    private Long advertisementId;


}
