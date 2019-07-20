package com.charlieworld.bookbug.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends Throwable {

    private HttpStatus statusCode;
    private String message;

    public CustomException(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
