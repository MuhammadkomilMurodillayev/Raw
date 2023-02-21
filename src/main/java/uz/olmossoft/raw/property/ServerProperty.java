package uz.olmossoft.raw.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Muhammadkomil Murodillayev, ср 18:36. 1/11/23
 */


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "storage")
public class ServerProperty {

    private String photoStoragePath;

}
