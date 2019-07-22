package com.charlieworld.bookbug.util;

import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.http.model.naver.Item;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NaverItemHelper {

    public static LocalDateTime parser(String str) {
        if (str.length() == 4) {
            int year = Integer.parseInt(str);
            return LocalDate.of(Integer.parseInt(str), 1, 1).atStartOfDay();
        } else if (str.length() == 6) {
            int year = Integer.parseInt(str.substring(0, 4));
            int month = Integer.parseInt(str.substring(4, 6));
            return LocalDate.of(year, month, 1).atStartOfDay();
        } else {
            int year = Integer.parseInt(str.substring(0, 4));
            int month = Integer.parseInt(str.substring(4, 6));
            int day = Integer.parseInt(str.substring(6, 8));
            return LocalDate.of(year, month, day).atStartOfDay();
        }
    }

    public static Book toBook(Item item) {
        return Book
                .builder()
                .name(item.getTitle())
                .isbn(item.getIsbn())
                .authors(item.getAuthor())
                .contents(item.getDescription())
                .publisher(item.getPublisher())
                .publishDatetime(parser(item.getPubdate()))
                .thumbnail(item.getImage())
                .price(item.getPrice())
                .salePrice(item.getDiscount())
                .build();
    }
}
