package top.ink.gpt.Enum;

import lombok.Getter;

/**
 * desc: role
 *
 * @author ink
 * date:2023-04-01 11:13
 */
@Getter
public enum Role {

    USER("user"),

    ASSISTANT("assistant"),

    SYSTEM("system")

    ;

    private String name;

    private Role(String name){
        this.name = name;
    }

}
