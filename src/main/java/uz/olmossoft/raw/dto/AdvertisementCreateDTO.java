package uz.olmossoft.raw.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import uz.olmossoft.raw.enums.Currency;

import java.io.Serializable;

/**
 * @author Muhammadkomil Murodillayev, ср 15:29. 1/11/23
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementCreateDTO implements Serializable {

    private MultipartFile photo;

    @NonNull
    private String name;

    private Double price;

    private Currency currency;

    private Double volume;

    private String description;

    private String phone;

    private Long regionId;

    private Long districtId;
}
