package com.charlieworld.bookbug.http.model.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Document {
    @JsonProperty("title")
    private String title;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("authors")
    private List<String> authors;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("price")
    private int price;

    @JsonProperty("sale_price")
    private int salePrice;

    @JsonProperty("thumbnail")
    private String thumbnail;

    public Document(
            @JsonProperty("title") String title,
            @JsonProperty("contents") String contents,
            @JsonProperty("isbn") String isbn,
            @JsonProperty("datetime") String datetime,
            @JsonProperty("authors") List<String> authors,
            @JsonProperty("publisher") String publisher,
            @JsonProperty("price") int price,
            @JsonProperty("sale_price") int salePrice,
            @JsonProperty("thumbnail") String thumbnail
    ) {
        this.title = title;
        this.contents = contents;
        this.isbn = isbn;
        this.datetime = datetime;
        this.authors = authors;
        this.publisher = publisher;
        this.price = price;
        this.salePrice = salePrice;
        this.thumbnail = thumbnail;
    }
}
