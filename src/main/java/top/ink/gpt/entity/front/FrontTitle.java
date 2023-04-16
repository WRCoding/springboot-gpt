package top.ink.gpt.entity.front;

import lombok.Builder;
import lombok.Data;

/**
 * desc: frontTitle
 *
 * @author ink
 * date:2023-04-15 22:21
 */
@Data
@Builder
public class FrontTitle {

    private String chatId;

    private String system;
}
