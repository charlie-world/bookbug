package com.charlieworld.bookbug.http.model.kakao;

import lombok.Data;

import java.io.Serializable;

@Data
public class Meta implements Serializable {
    private int totalCount;
    private int pageableCount;
    private boolean isEnd;
}
