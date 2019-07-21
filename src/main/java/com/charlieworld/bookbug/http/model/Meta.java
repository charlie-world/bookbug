package com.charlieworld.bookbug.http.model;

import lombok.Data;

@Data
public class Meta {
    private int totalCount;
    private int pageableCount;
    private boolean isEnd;
}
