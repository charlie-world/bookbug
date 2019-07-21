package com.charlieworld.bookbug.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class BookSimple implements JsonBase {
    private Long bookId;
    private String name;
    private String thumbnail;
    private List<String> authors;
    private int price;
    private int salePrice;

    @Builder
    public BookSimple(
            Long bookId,
            String name,
            String thumbnail,
            List<String> authors,
            int price,
            int salePrice
    ) {
        this.bookId = bookId;
        this.name = name;
        this.thumbnail = thumbnail;
        this.authors = authors;
        this.price = price;
        this.salePrice = salePrice;
    }
}
