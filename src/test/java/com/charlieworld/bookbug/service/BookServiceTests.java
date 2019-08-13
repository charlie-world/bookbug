package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.BookDetail;
import com.charlieworld.bookbug.dto.BookList;
import com.charlieworld.bookbug.dto.BookSimple;
import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.entity.History;
import com.charlieworld.bookbug.entity.Popular;
import com.charlieworld.bookbug.entity.TargetType;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.http.model.kakao.Document;
import com.charlieworld.bookbug.http.model.kakao.KakaoBookModel;
import com.charlieworld.bookbug.http.model.kakao.Meta;
import com.charlieworld.bookbug.repository.BookRepository;
import com.charlieworld.bookbug.repository.QueryCacheRepository;
import com.charlieworld.bookbug.util.ArrayHelper;
import com.charlieworld.bookbug.util.BookHelper;
import com.charlieworld.bookbug.util.KakaoDocumentHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTests {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private QueryCacheRepository queryCacheRepository;

    @MockBean
    private PopularService popularService;

    @MockBean
    private HistoryService historyService;

    @MockBean
    private KakaoBookHttpService kakaoBookHttpService;

    @Autowired
    private BookService bookService;

    @Test
    public void getBookDetailTest() {
        Long bookId = 1L;

        String title = "title";
        String contents = "contents";
        String isbn = "isbn";
        String datetime = "2019-01-01T00:00:00.000+09:00";
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

        BookDetail bookDetail = BookDetail
                .builder()
                .bookId(book.getBookId())
                .name(book.getName())
                .thumbnail(book.getThumbnail())
                .contents(book.getContents())
                .isbn(book.getIsbn())
                .authors(ArrayHelper.stringToArray(book.getAuthors()))
                .publisher(book.getPublisher())
                .publishDatetime(book.getPublishDatetime())
                .price(book.getPrice())
                .salePrice(book.getSalePrice())
                .build();

        given(bookRepository.findById(bookId))
                .willReturn(Optional.of(book));

        given(bookRepository.findById(2L))
                .willReturn(Optional.empty());

        try {
            assertEquals(
                    bookService.getBookDetail(bookId),
                    bookDetail
            );
        } catch (CustomException e) {
            e.printStackTrace();
        }

        boolean exceptionOccured = false;

        try {
            bookService.getBookDetail(2L);
        } catch (CustomException e) {
            exceptionOccured = true;
        }

        assertTrue(exceptionOccured);
    }

    @Test
    public void searchBooksTest() {
        Long userId = 1L;
        String queryWithCache = "query_with_cache";
        String queryWithNoCache = "query_with_no_cache";
        int page = 1;
        int total = 1000;
        TargetType targetType = TargetType.TITLE;

        List<BookSimple> bookSimples = new ArrayList<>();
        BookSimple bookSimple = BookSimple
                .builder()
                .bookId(1L)
                .name("name")
                .thumbnail("thumbnail")
                .authors(ArrayHelper.stringToArray("authorsStr"))
                .price(1)
                .salePrice(1)
                .build();
        bookSimples.add(bookSimple);

        BookList bookListWithCache = BookList
                .builder()
                .books(bookSimples)
                .isEnd(false)
                .page(page)
                .total(total)
                .build();

        History historyWithCache = History.builder().queryString(queryWithCache).userId(userId).build();
        History historyWithNoCache = History.builder().queryString(queryWithNoCache).userId(userId).build();

        Meta meta = new Meta(1, 1, true);
        List<Document> documents = new ArrayList<>();
        Document document = new Document(
                "title",
                "contents",
                "isbn2",
                "2019-01-01T00:00:00.000+09:00",
                new ArrayList<>(),
                "publisher",
                1,
                1,
                "thumbnail"
        );
        documents.add(document);

        KakaoBookModel kakaoBookModel = new KakaoBookModel(meta, documents);

        given(queryCacheRepository.getCachedBookList(targetType, queryWithCache, page))
                .willReturn(bookListWithCache);

        given(queryCacheRepository.getCachedBookList(targetType, queryWithNoCache, page))
                .willReturn(null);

        given(historyService.upsert(userId, queryWithCache))
                .willReturn(historyWithCache);

        given(historyService.upsert(userId, queryWithNoCache))
                .willReturn(historyWithNoCache);

        try {
            given(kakaoBookHttpService.search(page, queryWithNoCache, targetType))
                    .willReturn(kakaoBookModel);
        } catch (CustomException e) {
            e.printStackTrace();
        }

        given(bookRepository.findByIsbn("isbn"))
                .willReturn(Optional.empty());

        Book book = KakaoDocumentHelper.toBook(document);

        List<Book> books = new ArrayList<>();
        books.add(book);

        BookList bookListWithNoCache = BookList
                .builder()
                .books(BookHelper.toBookSimple(books))
                .isEnd(true)
                .page(page)
                .total(total)
                .build();

        given(bookRepository.findByIsbn(book.getIsbn()))
                .willReturn(Optional.of(book));

        given(queryCacheRepository.put(targetType, queryWithNoCache, page, kakaoBookModel.getMeta().getPageableCount(), kakaoBookModel.getMeta().isEnd(), books))
                .willReturn(bookListWithNoCache);

        Popular popularWithCache = Popular.builder().count(1L).queryString(queryWithCache).build();
        Popular popularWithNoCache = Popular.builder().count(1L).queryString(queryWithNoCache).build();

        given(popularService.upsert(queryWithCache))
                .willReturn(popularWithCache);

        given(popularService.upsert(queryWithNoCache))
                .willReturn(popularWithNoCache);

        boolean thrown = false;

        try {
            assertEquals(
                    bookService.searchBooks(userId, queryWithCache, page, targetType),
                    bookListWithCache
            );
        } catch (CustomException e) {
            thrown = true;
        }

        assertFalse(thrown);

        try {
            assertEquals(
                    bookService.searchBooks(userId, queryWithNoCache, page, targetType),
                    bookListWithNoCache
            );
        } catch (CustomException e) {
            thrown = true;
        }

        assertFalse(thrown);
    }
}
