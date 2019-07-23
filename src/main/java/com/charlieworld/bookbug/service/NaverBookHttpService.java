package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.entity.TargetType;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.http.model.naver.NaverBookModel;
import com.charlieworld.bookbug.http.model.naver.TargetKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
public class NaverBookHttpService implements HttpService {

    private final String apiPath = "/v1/search/book_adv.xml";
    @Value("${spring.naver.client-id}")
    private String naverClientId;
    @Value("${spring.naver.client-secret}")
    private String naverClientSecret;
    @Value("${spring.naver.host}")
    private String host;

    private String targetTypeMapper(TargetType targetType, String query) {
        TargetKey key;
        if (targetType == TargetType.TITLE) {
            key = TargetKey.TITLE_KEY;
        } else if (targetType == TargetType.ISBN) {
            key = TargetKey.ISBN_KEY;
        } else if (targetType == TargetType.PERSON) {
            key = TargetKey.PERSON_KEY;
        } else {
            key = TargetKey.PUBISHER_KEY;
        }
        return String.format("%s=%s", key.getValue(), query);
    }

    private HttpEntity makeAuthHeader() {
        String CLIENT_ID_HEADER_KEY = "X-Naver-Client-Id";
        String CLIENT_SECRET_HEADER_KEY = "X-Naver-Client-Secret";
        HttpHeaders header = new HttpHeaders();
        header.add(CLIENT_ID_HEADER_KEY, naverClientId);
        header.add(CLIENT_SECRET_HEADER_KEY, naverClientSecret);
        return new HttpEntity(header);
    }

    public NaverBookModel search(int page, String query, TargetType targetType) throws CustomException {
        NaverBookModel model;
        try {
            String params = String.format("?start=%d&%s", page * 10L, targetTypeMapper(targetType, query));
            String uri = host + apiPath + params;
            ResponseEntity<NaverBookModel> response = restTemplate
                    .exchange(uri, HttpMethod.GET, makeAuthHeader(), NaverBookModel.class);
            model = response.getBody();
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "서버와 통신 중 문제가 발생 하였습니다.");
        }
        return model;
    }
}
