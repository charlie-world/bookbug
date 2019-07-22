package com.charlieworld.bookbug.controller;

import com.charlieworld.bookbug.dto.BaseResponse;
import com.charlieworld.bookbug.dto.BookDetail;
import com.charlieworld.bookbug.dto.BookList;
import com.charlieworld.bookbug.dto.Meta;
import com.charlieworld.bookbug.entity.TargetType;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.service.BookService;
import com.charlieworld.bookbug.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/api/v1/books/{bookId}")
    public BaseResponse getBookDetail(
            @PathVariable("bookId") Long bookId,
            @RequestHeader(value = UserService.AUTH_KEY) String token
    ) {
        BookDetail bookDetail;
        BaseResponse<BookDetail> response;
        try {
            userService.authenticate(token);
            bookDetail = bookService.getBookDetail(bookId);
            response = new BaseResponse<>(bookDetail);
        } catch (CustomException e) {
            Meta meta = new Meta(e.getStatusCode(), e.getMessage());
            response = new BaseResponse<>(meta);
        }
        return response;
    }

    @GetMapping("/api/v1/books")
    public BaseResponse searchBooks(
            @RequestHeader(value = UserService.AUTH_KEY) String token,
            @RequestParam(value = "target-type") TargetType targetType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "query") String queryString
    ) {
        BaseResponse<BookList> response;
        try {
            Long userId = userService.authenticate(token);
            BookList bookList = bookService.searchBooks(userId, queryString, page, targetType);
            response = new BaseResponse<>(bookList);
        } catch (CustomException e) {
            Meta meta = new Meta(e.getStatusCode(), e.getMessage());
            response = new BaseResponse<>(meta);
        }
        return response;
    }
}
