package top.ink.gpt.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.ink.gpt.entity.common.Response;
import top.ink.gpt.entity.common.Usage;

/**
 * desc: chat
 *
 * @author ink
 * date:2023-04-01 11:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionResponse extends Response {
    private String id;
    private String object;
    private String model;
    private ChatChoice[] choices;
    private Usage usage;
}
