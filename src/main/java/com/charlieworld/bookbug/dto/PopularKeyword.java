package com.charlieworld.bookbug.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PopularKeyword implements JsonBase {
    private String queryString;
    private Long count;

    @Builder
    public PopularKeyword(String queryString, Long count) {
        this.queryString = queryString;
        this.count = count;
    }
}
