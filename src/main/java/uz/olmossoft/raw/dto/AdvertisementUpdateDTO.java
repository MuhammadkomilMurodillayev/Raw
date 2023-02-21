package uz.olmossoft.raw.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import uz.olmossoft.raw.enums.Currency;

/**
 * @author Muhammadkomil Murodillayev, ср 15:44. 1/11/23
 */

@Data
public class AdvertisementUpdateDTO {

    private Long id;

    private MultipartFile photo;

    private String name;

    private Double price;

    private Currency currency;

    private Double volume;

    private String description;

    private String phone;

    private Long regionId;

    private Long districtId;

}
