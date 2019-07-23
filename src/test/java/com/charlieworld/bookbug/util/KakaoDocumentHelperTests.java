package com.charlieworld.bookbug.util;

import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.http.model.kakao.Document;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class KakaoDocumentHelperTests {

    @Test
    public void kakaoDocumentHelperTest() {
        String title = "title";
        String contents = "contents";
        String isbn = "isbn";
        String datetime = "2019-01-01T00:00:00.000+09:00";
        ArrayList<String> authors = new ArrayList<>();
        authors.add("chanhee");
        String authorsStr = "chanhee";
        String publisher = "publisher";
        int price = 1;
        int salePrice = 1;
        String thumbnail = "thumbnail";

        Document document = new Document(
                title,
                contents,
                isbn,
                datetime,
                authors,
                publisher,
                price,
                salePrice,
                thumbnail
        );
        OffsetDateTime odt = OffsetDateTime.parse(datetime);
        Book book = Book
                .builder()
                .name(title)
                .isbn(isbn)
                .authors(authorsStr)
                .contents(contents)
                .publisher(publisher)
                .publishDatetime(odt.toLocalDateTime())
                .thumbnail(thumbnail)
                .price(price)
                .salePrice(salePrice)
                .build();
        Book expected = KakaoDocumentHelper.toBook(document);

        assertEquals(expected.getAuthors(), book.getAuthors());
        assertEquals(expected.getIsbn(), book.getIsbn());
        assertEquals(expected.getName(), book.getName());
        assertEquals(expected.getPublishDatetime(), book.getPublishDatetime());
        assertEquals(expected.getPublisher(), book.getPublisher());
        assertEquals(expected.getContents(), book.getContents());
        assertEquals(expected.getPrice(), book.getPrice());
        assertEquals(expected.getSalePrice(), book.getSalePrice());
    }
}
