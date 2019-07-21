package com.charlieworld.bookbug.http.model.kakao;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class Document implements Serializable {
    private String title;
    private String contents;
    private String isbn;
    private String datetime;
    private ArrayList<String> authors;
    private String publisher;
    private int price;
    private int salePrice;
    private String thumbnail;

    public Document(
            String title,
            String contents,
            String isbn,
            String datetime,
            ArrayList<String> authors,
            String publisher,
            int price,
            int salePrice,
            String thumbnail
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
