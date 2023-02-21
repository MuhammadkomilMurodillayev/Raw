package uz.olmossoft.raw.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Muhammadkomil Murodillayev, ср 17:02. 1/11/23
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "api.info")
public class OpenApiProperties {

    private String title;
    private String description;
    private String version;
    private String contactName;
    private String contactEmail;
    private String contactUrl;
}
