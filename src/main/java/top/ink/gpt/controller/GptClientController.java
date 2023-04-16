package top.ink.gpt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.POST;
import top.ink.gpt.Api.OpenAIApi;
import top.ink.gpt.Enum.Role;
import top.ink.gpt.entity.chat.ChatCompletionRequest;
import top.ink.gpt.entity.chat.ChatCompletionResponse;
import top.ink.gpt.entity.chat.ChatMessage;
import top.ink.gpt.entity.completion.CompletionRequest;
import top.ink.gpt.entity.completion.CompletionResponse;
import top.ink.gpt.entity.front.FrontChatMessage;
import top.ink.gpt.entity.front.FrontChatRequest;
import top.ink.gpt.entity.front.FrontImageRequest;
import top.ink.gpt.entity.image.ImageData;
import top.ink.gpt.service.ChatCompletionService;
import top.ink.gpt.service.ImageService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/gpt")
public class GptClientController {

    @Resource
    private ChatCompletionService service;

    @Resource
    private ImageService imageService;

//    @GetMapping("/completion")
//    public String completion(String content){
//        CompletionRequest completionRequest = CompletionRequest.builder().prompt(content).build();
//        CompletionResponse completionResponse = openAIApi.completion(completionRequest);
//        return completionResponse.getChoices()[0].getText();
//    }

    @PostMapping("/chat")
    public FrontChatMessage chat(@RequestBody FrontChatRequest frontChatRequest){
        return service.chat(frontChatRequest);
    }


    @GetMapping("/getMessages")
    public List<ChatMessage> getMessages(String chatId){
        return service.getChatMessage(chatId);
    }

    @GetMapping("/chatList")
    public List<String> chatList(){
        return service.chatList();
    }

    @PostMapping("/image")
    public List<ImageData> image(@RequestBody FrontImageRequest request){
        return imageService.image(request);
    }

}
