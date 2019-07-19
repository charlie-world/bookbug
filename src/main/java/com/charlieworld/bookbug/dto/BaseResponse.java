package com.charlieworld.bookbug.dto;

import lombok.Data;

@Data
public class BaseResponse<T> implements JsonBase {
    private T data;
    private Meta meta;
    private Pagination pagination;
}
