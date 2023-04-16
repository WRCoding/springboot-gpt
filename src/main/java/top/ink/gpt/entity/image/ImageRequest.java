package top.ink.gpt.entity.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import top.ink.gpt.entity.front.FrontImageRequest;

import java.util.Objects;

/**
 * desc: imageRequest
 *
 * @author ink
 * date:2023-04-05 22:41
 */
@Data
@Builder
public class ImageRequest {

    private String prompt;

    /**生成多少张图片, 范围1-10, 默认1*/
    @Builder.Default
    private Integer n = 1;

    @Builder.Default
    private String size = "512x512";

    @JsonProperty("response_format")
    @Builder.Default
    private String responseFormat = "b64_json";

    public static ImageRequest convert(FrontImageRequest request){
        if (Objects.isNull(request.getPrompt())){
            throw new RuntimeException("prompt must not be null");
        }
        ImageRequestBuilder builder = ImageRequest.builder();
        if (!Objects.isNull(request.getN())){
            builder.n(request.getN());
        }
        if (!Objects.isNull(request.getSize())){
            builder.size(request.getSize());
        }
        if (!Objects.isNull(request.getResponseFormat())){
            builder.responseFormat(request.getResponseFormat());
        }
        if (!Objects.isNull(request.getPrompt())){
            builder.prompt(request.getPrompt());
        }

        return builder.build();
    }
}
