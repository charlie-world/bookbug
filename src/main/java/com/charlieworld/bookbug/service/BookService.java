package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.BookDetail;
import com.charlieworld.bookbug.dto.BookSimple;
import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.entity.TargetType;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.http.model.kakao.Document;
import com.charlieworld.bookbug.http.model.kakao.KakaoBookModel;
import com.charlieworld.bookbug.repository.BookRepository;
import com.charlieworld.bookbug.util.ArrayHelper;
import com.charlieworld.bookbug.util.BookHelper;
import com.charlieworld.bookbug.util.CacheHelper;
import com.charlieworld.bookbug.util.KakaoDocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private PopularService popularService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private KakaoBookHttpService kakaoBookHttpService;

    abstract class BookList implements List<Book> {

    }

    @Transactional
    private List<Book> insertBooks(List<Book> books) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            Optional<Book> bookOpt = bookRepository.findByIsbn(book.getIsbn());
            if (!bookOpt.isPresent()) {
                result.add(bookRepository.save(book));
            } else {
                result.add(bookOpt.get());
            }
        }
        return result;
    }

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

    public List<BookSimple> searchBooks(
            Long userid,
            String queryString,
            int page,
            TargetType targetType
    ) throws CustomException {
        List<BookSimple> bookSimples = null;
        Cache cache = cacheManager.getCache(targetType.getValue());
        String cacheKey = CacheHelper.cacheKey(queryString, page);
        List<Book> cachedBooks;
        try {
            cachedBooks = cache.get(cacheKey, BookList.class);
        } catch (NullPointerException e) {
            cachedBooks = null;
        }
        if (cachedBooks == null) {
            try {
                KakaoBookModel kakaoBookModel = kakaoBookHttpService.getBooks(page, queryString, targetType);
                List<Book> books = new ArrayList<>();
                for (Document document : kakaoBookModel.getDocuments()) {
                    books.add(KakaoDocumentHelper.toBook(document));
                }
                List<Book> insertedBooks = insertBooks(books);
                cache.put(cacheKey, insertedBooks);
                bookSimples = BookHelper.toBookSimple(insertedBooks);
            } catch (CustomException e) {
                throw e;
                // failover 로 naverBookHttp 하는 액션
            }
        } else {
            bookSimples = BookHelper.toBookSimple(cachedBooks);
            historyService.insertHistory(userid, queryString);
        }
        popularService.updatePopular(queryString);
        return bookSimples;
    }
}
