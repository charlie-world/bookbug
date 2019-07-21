package com.charlieworld.bookbug.entity;

import lombok.Getter;

@Getter
public enum TargetType {
    TITLE("title"),
    ISBN("isbn"),
    PUBLISHER("publisher"),
    PERSON("person");

    private String value;

    TargetType(String value) {
        this.value = value;
    }
}
