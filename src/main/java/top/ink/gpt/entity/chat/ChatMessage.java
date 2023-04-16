package top.ink.gpt.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.ink.gpt.Enum.Role;

/**
 * desc:
 *
 * @author ink
 * date:2023-04-01 11:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Builder.Default
    private String role = Role.USER.getName();
    private String content;
}
