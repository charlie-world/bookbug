package com.charlieworld.bookbug.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookDetail implements JsonBase {

    private Long bookId;
    private String name;
    private String thumbnail;
    private String contents;
    private String isbn;
    private List<String> authors;
    private String publisher;
    private LocalDateTime publishDatetime;
    private int price;
    private int salePrice;

    @Builder
    public BookDetail(
            Long bookId,
            String name,
            String thumbnail,
            String contents,
            String isbn,
            List<String> authors,
            String publisher,
            LocalDateTime publishDatetime,
            int price,
            int salePrice
    ) {
        this.bookId = bookId;
        this.name = name;
        this.thumbnail = thumbnail;
        this.contents = contents;
        this.isbn = isbn;
        this.authors = authors;
        this.publisher = publisher;
        this.publishDatetime = publishDatetime;
        this.price = price;
        this.salePrice = salePrice;
    }
}
