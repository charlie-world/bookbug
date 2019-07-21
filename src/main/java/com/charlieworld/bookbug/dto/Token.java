package com.charlieworld.bookbug.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class Token implements JsonBase {
    private String token;

    @Builder
    public Token(String token) {
        this.token = token;
    }
}
