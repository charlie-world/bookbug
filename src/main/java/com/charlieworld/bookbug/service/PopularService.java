package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.PopularKeyword;
import com.charlieworld.bookbug.entity.Popular;
import com.charlieworld.bookbug.repository.PopularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopularService {

    @Autowired
    private PopularRepository popularRepository;

    public List<PopularKeyword> getPopularkeyword() {
        List<PopularKeyword> popularKeywordList = new ArrayList<>();
        List<Popular> populars = popularRepository.findTop10ByOrderByCountDesc();
        for (int rank = 0; rank < populars.size(); rank++) {
            Popular popular = populars.get(rank);
            PopularKeyword keyword = PopularKeyword
                    .builder()
                    .query(popular.getQuery().getQueryString())
                    .count(popular.getCount())
                    .build();
            popularKeywordList.add(keyword);
        }
        return popularKeywordList;
    }
}
