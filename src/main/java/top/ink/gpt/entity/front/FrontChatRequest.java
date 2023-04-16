package top.ink.gpt.entity.front;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.ink.gpt.entity.chat.ChatMessage;

/**
 * desc:
 *
 * @author ink
 * date:2023-04-02 17:06
 */
@Data
@AllArgsConstructor
public class FrontChatRequest {
    private String chatId;

    private String system;
    private String user;
}
