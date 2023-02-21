package uz.olmossoft.raw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.olmossoft.raw.enums.Currency;
import uz.olmossoft.raw.enums.ModStatus;
import uz.olmossoft.raw.enums.Status;

/**
 * @author Muhammadkomil Murodillayev, вс 00:08. 1/22/23
 */
@Setter
@Getter
@AllArgsConstructor
public class AdvertisementFilterDTO {

    private String name;

    private Double price;

    private Currency currency;

    private Double volume;

    private String phone;

    private Long regionId;

    private Long districtId;
    private Integer page;

    private Integer size;

    public AdvertisementFilterDTO() {
        this.page = 1;
        this.size = 10;
    }
}
