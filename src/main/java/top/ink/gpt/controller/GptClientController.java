package top.ink.gpt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ink.gpt.Api.OpenAIApi;
import top.ink.gpt.entity.completion.CompletionRequest;
import top.ink.gpt.entity.completion.CompletionResponse;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/gptclient")
public class GptClientController {

    @Resource
    private OpenAIApi openAIApi;

    @GetMapping("/completion")
    public void completion(String content){
        CompletionRequest completionRequest = CompletionRequest.builder().prompt(content).build();
        log.info("completionRequest: {}",completionRequest);
        CompletionResponse completionResponse = openAIApi.completion(completionRequest);
        log.info("completionResponse: {}",completionResponse);
    }
}
