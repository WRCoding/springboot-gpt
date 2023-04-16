package top.ink.gpt.entity.chat;

import lombok.Builder;
import lombok.Data;
import top.ink.gpt.Enum.Model;

import java.util.List;

/**
 * desc: chat
 *
 * @author ink
 * date:2023-04-01 11:03
 */
@Data
@Builder
public class ChatCompletionRequest {

    @Builder.Default
    private String model = Model.GPT_35_TURBO.getName();

    private List<ChatMessage> messages;
}
