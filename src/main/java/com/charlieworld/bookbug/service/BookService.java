package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.BookDetail;
import com.charlieworld.bookbug.dto.BookList;
import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.entity.TargetType;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.http.model.kakao.Document;
import com.charlieworld.bookbug.http.model.kakao.KakaoBookModel;
import com.charlieworld.bookbug.http.model.naver.Item;
import com.charlieworld.bookbug.http.model.naver.NaverBookModel;
import com.charlieworld.bookbug.repository.BookRepository;
import com.charlieworld.bookbug.repository.QueryCacheRepository;
import com.charlieworld.bookbug.util.ArrayHelper;
import com.charlieworld.bookbug.util.KakaoDocumentHelper;
import com.charlieworld.bookbug.util.NaverItemHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private QueryCacheRepository queryCacheRepository;

    @Autowired
    private PopularService popularService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private KakaoBookHttpService kakaoBookHttpService;

    @Autowired
    private NaverBookHttpService naverBookHttpService;

    public BookDetail getBookDetail(Long bookId) throws CustomException {
        BookDetail result = null;
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            result = BookDetail
                    .builder()
                    .bookId(book.get().getBookId())
                    .name(book.get().getName())
                    .thumbnail(book.get().getThumbnail())
                    .contents(book.get().getContents())
                    .isbn(book.get().getIsbn())
                    .authors(ArrayHelper.stringToArray(book.get().getAuthors()))
                    .publisher(book.get().getPublisher())
                    .publishDatetime(book.get().getPublishDatetime())
                    .price(book.get().getPrice())
                    .salePrice(book.get().getSalePrice())
                    .build();
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 정보를 찾을 수 없습니다.");
        }
        return result;
    }

    public BookList searchBooks(
            Long userId,
            String queryString,
            int page,
            TargetType targetType
    ) throws CustomException {
        BookList bookList;
        BookList cachedBookList = queryCacheRepository.getCachedBookList(targetType, queryString, page);
        if (cachedBookList == null) {
            try {
                KakaoBookModel kakaoBookModel = kakaoBookHttpService.search(page, queryString, targetType);
                List<Book> books = new ArrayList<>();
                for (Document document : kakaoBookModel.getDocuments()) {
                    books.add(KakaoDocumentHelper.toBook(document));
                }
                List<Book> insertedBooks = bookRepository.saveAll(books);
                bookList = queryCacheRepository
                        .put(targetType, queryString, page, kakaoBookModel.getMeta().isEnd(), insertedBooks);
                historyService.upsert(userId, queryString);
            } catch (CustomException e) {
                NaverBookModel naverBookModel = naverBookHttpService.search(page, queryString, targetType);
                List<Book> books = new ArrayList<>();
                for (Item item : naverBookModel.getItems()) {
                    books.add(NaverItemHelper.toBook(item));
                }
                List<Book> insertedBooks = bookRepository.saveAll(books);
                boolean isEnd = naverBookModel.getTotal() <= naverBookModel.getStart() + naverBookModel.getDisplay();
                bookList = queryCacheRepository
                        .put(targetType, queryString, page, isEnd, insertedBooks);
            }
        } else {
            bookList = cachedBookList;
            historyService.upsert(userId, queryString);
        }
        popularService.upsert(queryString);
        return bookList;
    }
}
