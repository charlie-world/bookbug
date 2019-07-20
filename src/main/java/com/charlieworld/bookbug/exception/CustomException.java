package com.charlieworld.bookbug.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Throwable {

    private HttpStatus statusCode;
    private String message;

    public CustomException(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
