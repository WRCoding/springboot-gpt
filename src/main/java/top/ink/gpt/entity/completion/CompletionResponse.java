package top.ink.gpt.entity.completion;

import lombok.Data;
import top.ink.gpt.entity.common.Choice;
import top.ink.gpt.entity.common.Response;
import top.ink.gpt.entity.common.Usage;
/**
 * @author wanglongjun
 */
@Data
public class CompletionResponse extends Response {

    private String id;
    private String object;
    private String model;
    private Choice[] choices;
    private Usage usage;
}
