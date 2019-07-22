package com.charlieworld.bookbug.http.model.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class KakaoBookModel {
    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("documents")
    private List<Document> documents;

    public KakaoBookModel(@JsonProperty("meta") Meta meta, @JsonProperty("documents") List<Document> documents) {
        this.meta = meta;
        this.documents = documents;
    }
}
