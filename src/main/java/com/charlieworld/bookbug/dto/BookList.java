package com.charlieworld.bookbug.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class BookList implements JsonBase {
    private List<BookSimple> books;
    private int page;
    private int total;
    private boolean isEnd;

    @Builder
    public BookList(List<BookSimple> books, int page, int total, boolean isEnd) {
        this.books = books;
        this.page = page;
        this.total = total;
        this.isEnd = isEnd;
    }
}
