package top.ink.gpt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ink.gpt.Api.OpenAIApi;
import top.ink.gpt.entity.front.FrontImageRequest;
import top.ink.gpt.entity.image.ImageData;
import top.ink.gpt.entity.image.ImageRequest;
import top.ink.gpt.entity.image.ImageResponse;

import javax.annotation.Resource;
import java.util.List;

/**
 * desc: imageService
 *
 * @author ink
 * date:2023-04-05 22:49
 */
@Service
@Slf4j
public class ImageService {

    @Resource
    private OpenAIApi openAIApi;

    public List<ImageData> image(FrontImageRequest request){
        ImageRequest imageRequest = ImageRequest.convert(request);
        log.info("imageRequest: {}", imageRequest);
        ImageResponse imageResponse = openAIApi.image(imageRequest);
        log.info("imageResponse: {}", imageResponse);
        return imageResponse.getData();
    }
}
