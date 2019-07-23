package com.charlieworld.bookbug.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class BookList implements JsonBase {
    private List<BookSimple> books;
    private int page;
    private boolean isEnd;

    @Builder
    public BookList(List<BookSimple> books, int page, boolean isEnd) {
        this.books = books;
        this.page = page;
        this.isEnd = isEnd;
    }
}
