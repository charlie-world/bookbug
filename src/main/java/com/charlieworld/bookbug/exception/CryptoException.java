package com.charlieworld.bookbug.exception;

public class CryptoException extends Throwable {

    private String message;

    public CryptoException(String message) {
        super();
        this.message = message;
    }
}
