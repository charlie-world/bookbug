package com.charlieworld.bookbug.entity;

import lombok.Getter;

@Getter
public enum Target {
    TITLE("title"),
    ISBN("isbn"),
    PUBLISHER("publisher"),
    PERSON("person");

    private String value;

    Target(String value) {
        this.value = value;
    }
}
