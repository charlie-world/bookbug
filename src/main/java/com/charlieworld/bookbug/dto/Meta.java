package com.charlieworld.bookbug.dto;

import lombok.Data;

@Data
public class Meta implements JsonBase {
    private String resultCode;
    private String resultMsg;
}
