package uz.olmossoft.raw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.Where;
import uz.olmossoft.raw.enums.Currency;
import uz.olmossoft.raw.enums.ModStatus;
import uz.olmossoft.raw.enums.Status;

import java.time.LocalDateTime;

/**
 * @author Muhammadkomil Murodillayev, вт 21:36. 1/10/23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Where(clause = "deleted = false")
public class Advertisement extends Auditable {

    @Column(nullable = false)
    private String name;

    private Double price;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private Double volume;

    private String description;

    private String phone;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "district_id")
    private Long districtId;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar default 'IN_ACTIVE'")
    private Status status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "mod_status", columnDefinition = "varchar default 'IN_PROCESS'")
    private ModStatus modStatus;

    @Column(name = "order_time", columnDefinition = "timestamp default now()")
    private LocalDateTime orderTime;

}
