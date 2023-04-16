package top.ink.gpt.Enum;

import lombok.Getter;

@Getter
public enum Model {
    TEXT_DAVINCI_003("text-davinci-003"),
    TEXT_DAVINCI_002("text-davinci-002"),
    DAVINCI("davinci"),
    GPT_35_TURBO("gpt-3.5-turbo")

    ;
    private String name;

    Model(String name) {
        this.name = name;
    }
}
