package com.charlieworld.bookbug.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserHistory {
    private String keyword;
    private LocalDateTime historyDateTime;

    @Builder
    public UserHistory(String keyword, LocalDateTime historyDateTime) {
        this.keyword = keyword;
        this.historyDateTime = historyDateTime;
    }
}
