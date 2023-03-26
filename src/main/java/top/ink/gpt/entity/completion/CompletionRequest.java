package top.ink.gpt.entity.completion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;
import top.ink.gpt.Enum.Model;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionRequest {
    @Builder.Default
    private String model = Model.TEXT_DAVINCI_003.name();

    private String prompt;

    /**
     * 完成输出后的后缀，用于格式化输出结果
     */
    private String suffix;

    @Builder.Default
    @JsonProperty(value = "max_tokens")
    private Integer maxToken = 2048;

    @Builder.Default
    private double temperature = 0;

    /**
     * 使用温度采样的替代方法称为核心采样，其中模型考虑具有top_p概率质量的令牌的结果。因此，0.1 意味着只考虑包含前 10% 概率质量的代币。
     * <p>
     * 我们通常建议更改此设置，但不要同时更改两者。temperature
     */
    @JsonProperty("top_p")
    @Builder.Default
    private Double topP = 1d;

    /**
     * 为每个提示生成的完成次数。
     */
    @Builder.Default
    private Integer n = 1;

    @Builder.Default
    private boolean stream = false;
    /**
     * 最大值：5
     */
    private Integer logprobs;

    @Builder.Default
    private boolean echo = false;

    private List<String> stop;

    @JsonProperty("presence_penalty")
    @Builder.Default
    private double presencePenalty = 0;

    /**
     * -2.0 ~~ 2.0
     */
    @JsonProperty("frequency_penalty")
    @Builder.Default
    private double frequencyPenalty = 0;

    @JsonProperty("best_of")
    @Builder.Default
    private Integer bestOf = 1;

    @JsonProperty("logit_bias")
    private Map logitBias;
    /**
     * 用户唯一值，确保接口不被重复调用
     */
    private String user;

}
