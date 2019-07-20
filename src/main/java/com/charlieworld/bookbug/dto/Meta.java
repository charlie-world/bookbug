package com.charlieworld.bookbug.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Meta implements JsonBase {
    private int resultCode;
    private String resultMsg;

    public Meta(HttpStatus httpStatus, String resultMsg) {
        this.resultCode = httpStatus.value();
        this.resultMsg = resultMsg;
    }
}
