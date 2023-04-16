package top.ink.gpt.entity.config;

import lombok.Builder;
import lombok.Data;
import top.ink.gpt.entity.chat.ChatMessage;

import java.util.List;

/**
 * desc: localConfig
 *
 * @author ink
 * date:2023-04-09 21:18
 */
@Data
@Builder
public class LocalConfig {

    private String title;

    private List<ChatMessage> chatMessageList;
}
