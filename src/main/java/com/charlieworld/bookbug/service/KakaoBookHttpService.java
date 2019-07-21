package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.entity.TargetType;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.http.model.kakao.KakaoBookModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaoBookHttpService {

    @Value("${spring.kakao-application.rest-api-key}")
    private String kakaoAppKey;

    public KakaoBookModel getBooks(int page, String query, TargetType targetType) throws CustomException {
        return null;
    }
}
