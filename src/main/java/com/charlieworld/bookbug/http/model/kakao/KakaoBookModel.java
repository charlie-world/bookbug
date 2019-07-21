package com.charlieworld.bookbug.http.model.kakao;

import lombok.Data;

import java.util.List;

@Data
public class KakaoBookModel {
    private Meta meta;
    private List<Document> documents;
}
