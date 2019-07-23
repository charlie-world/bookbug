package com.charlieworld.bookbug.util;

import com.charlieworld.bookbug.dto.BookSimple;
import com.charlieworld.bookbug.entity.Book;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookHelperTests {

    @Test
    public void toBookSimpleTest() {
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

        List<Book> input = new ArrayList<>();
        input.add(book);

        BookSimple bookSimple = BookSimple
                .builder()
                .bookId(null)
                .name(title)
                .thumbnail(thumbnail)
                .authors(ArrayHelper.stringToArray(authorsStr))
                .price(price)
                .salePrice(salePrice)
                .build();

        List<BookSimple> expect = new ArrayList<>();
        expect.add(bookSimple);

        Assert.assertEquals(BookHelper.toBookSimple(input), expect);
    }
}
