package top.ink.gpt.entity.completion;

import lombok.Data;
import top.ink.gpt.entity.Choice;
import top.ink.gpt.entity.Response;
import top.ink.gpt.entity.Usage;
@Data
public class CompletionResponse extends Response {

    private String id;
    private String object;
    private String model;
    private Choice[] choices;
    private Usage usage;
}
