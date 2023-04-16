package top.ink.gpt.entity.common;

import lombok.Data;

@Data
public class Response {
    private long created = System.currentTimeMillis();
}
