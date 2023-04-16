package top.ink.gpt.entity.image;

import lombok.Data;
import top.ink.gpt.entity.common.Response;

import java.util.List;

/**
 * desc: imageResponse
 *
 * @author ink
 * date:2023-04-05 22:46
 */
@Data
public class ImageResponse extends Response {

    private List<ImageData> data;
}
