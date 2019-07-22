package com.charlieworld.bookbug.http.model.naver;

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
}
