package com.charlieworld.bookbug.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PopularKeyword implements JsonBase {
    private String query;
    private Long count;

    @Builder
    public PopularKeyword(String query, Long count) {
        this.query = query;
        this.count = count;
    }
}
