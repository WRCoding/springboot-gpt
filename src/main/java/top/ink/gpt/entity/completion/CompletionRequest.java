package top.ink.gpt.entity.completion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.ink.gpt.Enum.Model;

import java.util.List;
import java.util.Map;

/**
 * @author wanglongjun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionRequest {
    @Builder.Default
    private String model = Model.TEXT_DAVINCI_003.getName();

    private String prompt;

    @Builder.Default
    @JsonProperty(value = "max_tokens")
    private Integer maxToken = 2048;

}
