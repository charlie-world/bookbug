package com.charlieworld.bookbug.util;

import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.http.model.naver.Item;
import org.junit.Test;

import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;

public class NaverItemHelperTests {

    @Test
    public void dateParserTest() {
        String dateType1 = "2019";
        assertEquals(
                NaverItemHelper.dateParser(dateType1),
                LocalDateTime.of(2019, 1, 1, 0, 0)
        );

        String dateType2 = "201902";
        assertEquals(
                NaverItemHelper.dateParser(dateType2),
                LocalDateTime.of(2019, 2, 1, 0, 0)
        );

        String dateType3 = "20190307";
        assertEquals(
                NaverItemHelper.dateParser(dateType3),
                LocalDateTime.of(2019, 3, 7, 0, 0)
        );
    }

    @Test
    public void toBookTest() {
        String title = "title";
        String contents = "contents";
        String isbn = "isbn";
        String datetime = "20190101";
        String author = "chanhee";
        String publisher = "publisher";
        int price = 1;
        int salePrice = 1;
        String thumbnail = "thumbnail";

        Item item = Item
                .builder()
                .title(title)
                .description(contents)
                .isbn(isbn)
                .pubdate(datetime)
                .author(author)
                .publisher(publisher)
                .price(price)
                .discount(salePrice)
                .image(thumbnail)
                .build();

        Book expected = NaverItemHelper.toBook(item);

        assertEquals(expected.getAuthors(), item.getAuthor());
        assertEquals(expected.getIsbn(), item.getIsbn());
        assertEquals(expected.getName(), item.getTitle());
        assertEquals(expected.getPublishDatetime(), NaverItemHelper.dateParser(item.getPubdate()));
        assertEquals(expected.getPublisher(), item.getPublisher());
        assertEquals(expected.getContents(), item.getDescription());
        assertEquals(expected.getPrice(), item.getPrice());
        assertEquals(expected.getSalePrice(), item.getDiscount());
    }
}
