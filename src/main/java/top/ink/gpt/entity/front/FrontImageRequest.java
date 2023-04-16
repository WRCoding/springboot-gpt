package top.ink.gpt.entity.front;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * desc: frontImageRequest
 *
 * @author ink
 * date:2023-04-05 22:50
 */
@Data
public class FrontImageRequest {
    private String prompt;

    private Integer n;

    private String size;

    private String responseFormat;
}
