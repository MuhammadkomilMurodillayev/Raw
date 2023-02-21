package uz.olmossoft.raw.dto;

/**
 * @author Muhammadkomil Murodillayev, ср 15:34. 1/11/23
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDTO<T> implements Serializable {

    protected T data;
    protected boolean success;

    private Integer totalCount;

    private Integer pageCount;

    public DataDTO(T data) {
        this.data = data;
        this.success = true;
    }
    public DataDTO(T data, int pageCount) {
        this(data);
        this.pageCount = pageCount;
    }
}

