package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.BookDetail;
import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.repository.BookRepository;
import com.charlieworld.bookbug.util.ArrayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

//    @Autowired
//    private KakaoBook kakaoBook;

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
                    .authors(ArrayMapper.stringToArray(book.get().getAuthors()))
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

//    public void insertBooks(ArrayList<String> isbns) throws CustomException {
//        for (int i = 0; i < isbns.size(); i++) {
//            Optional<Book> book = bookRepository.findByisbn(isbns.get(i));
//            if (!book.isPresent()) {
//                Book newBook = Book
//                        .builder()
//
//                bookRepository.save();
//            }
//        }
//    }
}
