package uz.olmossoft.raw.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.Resource;

import java.util.UUID;

/**
 * @author Muhammadkomil Murodillayev, ср 12:09. 1/11/23
 */

@Data
@Builder
public class Upload {

    private UUID id;

    private String originalName;

    private String generatedName;

    private String contentType;

    private Long size;

    private String path;

    private Resource resource;




}
