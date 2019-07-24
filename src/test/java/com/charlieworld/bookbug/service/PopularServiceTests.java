package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.PopularKeyword;
import com.charlieworld.bookbug.entity.Popular;
import com.charlieworld.bookbug.repository.PopularRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PopularServiceTests {

    @MockBean
    private PopularRepository popularRepository;

    @Autowired
    private PopularService popularService;

    @Test
    public void findPopularkeywordTest() {
        List<Popular> top10 = new ArrayList<>();
        Popular popular = Popular.builder().count(100L).queryString("test").build();
        top10.add(popular);

        given(popularRepository.findTop10ByOrderByCountDesc())
                .willReturn(top10);

        List<PopularKeyword> popularKeywords = new ArrayList<>();
        popularKeywords.add(PopularKeyword.builder().count(popular.getCount()).queryString(popular.getQueryString()).build());
        assertEquals(
                popularService.findPopularkeyword(),
                popularKeywords
        );
    }

    @Test
    public void upsertTest() {
        String queryString = "query_string";
        Popular popular = Popular.builder().queryString(queryString).count(20L).popularId(1L).build();
        Popular updated = Popular
                .builder()
                .queryString(popular.getQueryString())
                .count(popular.getCount() + 1L)
                .popularId(popular.getPopularId())
                .build();
        given(popularRepository.findByQueryString(queryString))
                .willReturn(Optional.of(popular));

        given(popularRepository.save(updated))
                .willReturn(updated);

        popularService.upsert(queryString);
    }
}
