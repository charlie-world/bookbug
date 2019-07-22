package com.charlieworld.bookbug.http.model.naver;

import lombok.Getter;

@Getter
public enum TargetKey {
    TITLE_KEY("d_titl"),
    ISBN_KEY("d_isbn"),
    PERSON_KEY("d_auth"),
    PUBISHER_KEY("d_publ");

    private String value;

    TargetKey(String value) {
        this.value = value;
    }
}
