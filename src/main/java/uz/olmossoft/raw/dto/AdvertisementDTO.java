package uz.olmossoft.raw.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import uz.olmossoft.raw.enums.Currency;
import uz.olmossoft.raw.enums.ModStatus;
import uz.olmossoft.raw.enums.Status;

import java.util.List;

/**
 * @author Muhammadkomil Murodillayev, ср 15:41. 1/11/23
 */

@Data
@Builder
public class AdvertisementDTO {

    private Long id;

    private String name;

    private Double price;

    private Currency currency;

    private Double volume;

    private String description;

    private String phone;

    private Long regionId;

    private Long districtId;

    private Status status;

    private ModStatus modStatus;

    private AdvertisementPhotoDTO photo;
}
