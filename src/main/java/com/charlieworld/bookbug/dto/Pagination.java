package com.charlieworld.bookbug.dto;

import lombok.Data;

@Data
public class Pagination implements JsonBase {
    private boolean isEnd;
    private int index;
    private int total;
}
