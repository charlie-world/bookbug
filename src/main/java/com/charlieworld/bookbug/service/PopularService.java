package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.PopularKeyword;
import com.charlieworld.bookbug.entity.Popular;
import com.charlieworld.bookbug.repository.PopularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PopularService {

    @Autowired
    private PopularRepository popularRepository;

    public List<PopularKeyword> findPopularkeyword() {
        List<PopularKeyword> popularKeywordList = new ArrayList<>();
        List<Popular> populars = popularRepository.findTop10ByOrderByCountDesc();
        for (Popular popular : populars) {
            PopularKeyword keyword = PopularKeyword
                    .builder()
                    .query(popular.getQueryString())
                    .count(popular.getCount())
                    .build();
            popularKeywordList.add(keyword);
        }
        return popularKeywordList;
    }

    @Transactional
    public void upsert(String query) {
        Optional<Popular> popularOpt = popularRepository.findByQueryString(query);
        if (!popularOpt.isPresent()) {
            popularRepository.save(Popular.builder().count(1L).queryString(query).build());
        } else {
            Popular popular = popularOpt.get();
            popular.setCount(popular.getCount() + 1L);
            popularRepository.save(popular);
        }
    }
}
