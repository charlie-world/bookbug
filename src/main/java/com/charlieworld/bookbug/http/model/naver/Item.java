package com.charlieworld.bookbug.http.model.naver;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "item")
public class Item {
    private String title;
    private String link;
    private String image;
    private String author;
    private int price;
    private int discount;
    private String publisher;
    private String isbn;
    private String pubdate;
    private String description;

    @Builder
    public Item(
            String title,
            String link,
            String image,
            String author,
            int price,
            int discount,
            String publisher,
            String isbn,
            String pubdate,
            String description
    ) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.author = author;
        this.price = price;
        this.discount = discount;
        this.publisher = publisher;
        this.isbn = isbn;
        this.pubdate = pubdate;
        this.description = description;
    }
}
