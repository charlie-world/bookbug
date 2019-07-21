package com.charlieworld.bookbug.http;

import com.charlieworld.bookbug.entity.Query;
import com.charlieworld.bookbug.http.model.KakaoBookModel;
import com.charlieworld.bookbug.entity.Target;
import com.charlieworld.bookbug.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

public class KakaoBook {

//    @Value("${spring.kakao-application.rest-api-key}")
//    private String kakaoAppKey;
//
//    @Autowired
//    private QueryRepository queryRepository;
//
////    public KakaoBookModel getBooks(int page, String query, Target target) {
////        Optional<Query> queryOpt = queryRepository.findByQueryStrNPageNTarget(query, page, target);
////        if (queryOpt.isPresent()) {
////
////        }
////        return null;
////    }
}
