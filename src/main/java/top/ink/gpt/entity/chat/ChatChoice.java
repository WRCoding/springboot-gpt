package top.ink.gpt.entity.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author wanglongjun
 */
@Data
public class ChatChoice {
    private long index;
    private ChatMessage message;
    @JsonProperty("finish_reason")
    private String finishReason;
}
