package top.ink.gpt.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.ink.gpt.Api.OpenAIApi;
import top.ink.gpt.entity.chat.ChatCompletionRequest;
import top.ink.gpt.entity.chat.ChatCompletionResponse;
import top.ink.gpt.entity.chat.ChatMessage;
import top.ink.gpt.entity.config.LocalConfig;
import top.ink.gpt.entity.front.FrontChatMessage;
import top.ink.gpt.entity.front.FrontChatRequest;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * desc: service
 *
 * @author ink
 * date:2023-04-02 09:02
 */
@Service
@Slf4j
public class ChatCompletionService {

    @Value("${chatMessage.location}")
    private String location;

    private static final String SUFFIX = ".json";

    private static final String TITLE_PROMPT = "请为以下对话命名，优先使用中文，直接返回纯名称，去掉逗号，长度在15个字以内。对话内容：";


    @Resource
    private OpenAIApi openAIApi;

    private final ThreadPoolExecutor EXECUTOR
            = new ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), r -> new Thread(r, "write Thread"));


    public FrontChatMessage chat(FrontChatRequest frontChatRequest) {
        String chatId = frontChatRequest.getChatId();
        List<ChatMessage> chatMessages = new ArrayList<>();
        Optional<LocalConfig> localConfig = getLocalConfig(chatId);
        localConfig.ifPresent(config -> chatMessages.addAll(config.getChatMessageList()));
        chatMessages.addAll(generateChatMessage(frontChatRequest));
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder().messages(chatMessages).build();
        FrontChatMessage frontChatMessage = sendRequest(chatCompletionRequest);
        log.info(frontChatMessage.toString());
        chatMessages.add(frontChatMessage.getChatMessage());
        chatId = initTitle(localConfig,chatMessages);
        if (!localConfig.isPresent()){
            frontChatMessage.setNew(true);
        }
        frontChatMessage.setChatId(chatId);
        overrideMessage(chatMessages, chatId);
        return frontChatMessage;
    }

    private List<ChatMessage> generateChatMessage(FrontChatRequest frontChatRequest) {
        List<ChatMessage> chatMessageList = new ArrayList<>();
        if (StringUtils.hasText(frontChatRequest.getSystem())) {
            ChatMessage system = ChatMessage.builder().role("system").content(frontChatRequest.getSystem()).build();
            chatMessageList.add(system);
        }
        ChatMessage user = ChatMessage.builder().content(frontChatRequest.getUser()).build();
        chatMessageList.add(user);
        return chatMessageList;
    }

    private FrontChatMessage sendRequest(ChatCompletionRequest chatCompletionRequest) {
        ChatCompletionResponse chatCompletionResponse = openAIApi.chatCompletion(chatCompletionRequest);
        ChatMessage assistantMessage = chatCompletionResponse.getChoices()[0].getMessage();
        FrontChatMessage frontChatMessage = FrontChatMessage.builder().chatMessage(assistantMessage).build();
        return frontChatMessage;
    }

    private String initTitle(Optional<LocalConfig> localConfig, List<ChatMessage> chatMessages) {
        if (localConfig.isPresent()){
            return localConfig.get().getTitle();
        }else{
            String content = chatMessages.stream().map(ChatMessage::getContent).collect(Collectors.joining("\n"));
            String title = getTitle(content);
            log.info("title: {}", title);
            return title;
        }
    }

    private String getTitle(String content) {
        ChatMessage system = ChatMessage.builder().role("system").content("你是一个经验丰富的记录员，擅长从对话中提取关键信息，并为对话命名。").build();
        ChatMessage user = ChatMessage.builder().role("user").content(TITLE_PROMPT + content).build();
        ChatCompletionRequest request = ChatCompletionRequest.builder().messages(Arrays.asList(system, user)).build();
        return openAIApi.chatCompletion(request).getChoices()[0].getMessage().getContent();
    }

    public List<ChatMessage> getChatMessage(String chatId) {
        Optional<LocalConfig> localConfig = getLocalConfig(chatId);
        return localConfig.isPresent() ? localConfig.get().getChatMessageList() : Collections.emptyList();
    }

    private Optional<LocalConfig> getLocalConfig(String chatId) {
        LocalConfig localConfig = null;
        try {
            StringBuilder configJson = new StringBuilder();
            String path = getPath(chatId);
            File file = new File(path);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath())))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        configJson.append(line);
                    }
                }
            }
            if (StringUtils.hasText(configJson)) {
                localConfig = JSONUtil.toBean(configJson.toString(), LocalConfig.class);
            }
        } catch (IOException e) {
            log.error("获取历史数据出错,将不会传入历史数据, e: {}", e.getMessage());
        }
        return Optional.ofNullable(localConfig);
    }

    private String getPath(String chatId) {
        return location + chatId + SUFFIX;
    }

    private void overrideMessage(List<ChatMessage> chatMessages, String chatId) {
        EXECUTOR.execute(() -> {
            LocalConfig config = LocalConfig.builder().title(chatId).chatMessageList(chatMessages).build();
            String configJson = JSONUtil.parse(config).toString();
            log.info("config: {}",configJson);
            FileWriter writer = new FileWriter(getPath(chatId));
            writer.write(configJson, false);
        });
    }

    public List<String> chatList() {
        File files = new File(location);
        List<String> list = new ArrayList<>();
        if (files.exists() && files.isDirectory()){
            for (File file : Objects.requireNonNull(files.listFiles())) {
                FileReader reader = new FileReader(file);
                String configJson = reader.readString();
                LocalConfig localConfig = JSONUtil.toBean(configJson, LocalConfig.class);
                list.add(localConfig.getTitle());
            }
        }
        return list;
    }
}
