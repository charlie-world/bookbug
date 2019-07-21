package com.charlieworld.bookbug.http.model.kakao;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class KakaoBookModel implements Serializable {
    private Meta meta;
    private List<Document> documents;
}
