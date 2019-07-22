package com.charlieworld.bookbug;

import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.http.model.naver.Item;
import com.charlieworld.bookbug.util.NaverItemHelper;
import org.junit.Test;

import java.time.LocalDateTime;

public class NaverItemHelperTests {

    @Test
    public void dateParserTest() {
        String dateType1 = "2019";
        assert NaverItemHelper
                .dateParser(dateType1)
                .equals(LocalDateTime.of(2019, 1, 1, 0, 0));

        String dateType2 = "201902";
        assert NaverItemHelper
                .dateParser(dateType2)
                .equals(LocalDateTime.of(2019, 2, 1, 0, 0));

        String dateType3 = "20190307";
        assert NaverItemHelper
                .dateParser(dateType3)
                .equals(LocalDateTime.of(2019, 3, 7, 0, 0));
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

        assert expected.getAuthors().equals(item.getAuthor());
        assert expected.getIsbn().equals(item.getIsbn());
        assert expected.getName().equals(item.getTitle());
        assert expected.getPublishDatetime().equals(NaverItemHelper.dateParser(item.getPubdate()));
        assert expected.getPublisher().equals(item.getPublisher());
        assert expected.getContents().equals(item.getDescription());
        assert expected.getPrice() == item.getPrice();
        assert expected.getSalePrice() == item.getDiscount();
    }
}
