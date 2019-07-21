package com.charlieworld.bookbug.util;

import com.charlieworld.bookbug.dto.BookSimple;
import com.charlieworld.bookbug.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookHelper {

    public static List<BookSimple> toBookSimple(List<Book> books) {
        List<BookSimple> bookSimples = new ArrayList<>();
        for (Book book : books) {
            BookSimple bookSimple = BookSimple
                    .builder()
                    .bookId(book.getBookId())
                    .name(book.getName())
                    .thumbnail(book.getThumbnail())
                    .authors(ArrayHelper.stringToArray(book.getAuthors()))
                    .price(book.getPrice())
                    .salePrice(book.getSalePrice())
                    .build();
            bookSimples.add(bookSimple);
        }
        return bookSimples;
    }
}
