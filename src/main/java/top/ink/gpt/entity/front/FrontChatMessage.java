package top.ink.gpt.entity.front;

import lombok.Builder;
import lombok.Data;
import top.ink.gpt.entity.chat.ChatMessage;

/**
 * desc: frontChatMessage
 *
 * @author ink
 * date:2023-04-14 22:17
 */
@Data
@Builder
public class FrontChatMessage {
    private String chatId;

    private boolean isNew;

    private ChatMessage chatMessage;
}
