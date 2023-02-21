package uz.olmossoft.raw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import uz.olmossoft.raw.enums.Gender;
import uz.olmossoft.raw.enums.Role;

/**
 * @author Muhammadkomil Murodillayev, вт 22:27. 1/10/23
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "deleted = false")
public class AuthUser extends Auditable {

    private String name;

    private String surname;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String phone;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;



}
