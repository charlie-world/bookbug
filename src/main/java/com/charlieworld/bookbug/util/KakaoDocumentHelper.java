package com.charlieworld.bookbug.util;

import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.http.model.kakao.Document;

import java.time.OffsetDateTime;

public class KakaoDocumentHelper {
    public static Book toBook(Document document) {
        OffsetDateTime odt = OffsetDateTime.parse(document.getDatetime());
        return Book
                .builder()
                .name(document.getTitle())
                .isbn(document.getIsbn())
                .authors(ArrayHelper.arrayToString(document.getAuthors()))
                .contents(document.getContents())
                .publisher(document.getPublisher())
                .publishDatetime(odt.toLocalDateTime())
                .thumbnail(document.getThumbnail())
                .price(document.getPrice())
                .salePrice(document.getSalePrice())
                .build();
    }
}
