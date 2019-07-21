package com.charlieworld.bookbug.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse<T> implements JsonBase {
    private T data;
    private Meta meta;
    private Pagination pagination;

    public BaseResponse(T data) {
        this.data = data;
        this.meta = new Meta(HttpStatus.OK, "success");
    }

    public BaseResponse(Meta meta) {
        this.meta = meta;
    }

    public BaseResponse(T data, Pagination pagination) {
        this.data = data;
        this.meta = new Meta(HttpStatus.OK, "success");
        this.pagination = pagination;
    }
}
