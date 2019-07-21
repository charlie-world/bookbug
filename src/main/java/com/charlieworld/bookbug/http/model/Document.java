package com.charlieworld.bookbug.http.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Document {
    private String title;
    private String contents;
    private String isbn;
    private String datetime;
    private ArrayList<String> authors;
    private String publisher;
    private int price;
    private int salePrice;
    private String thumbnail;
}
