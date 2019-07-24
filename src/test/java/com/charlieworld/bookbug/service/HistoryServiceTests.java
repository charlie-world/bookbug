package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.UserHistory;
import com.charlieworld.bookbug.entity.History;
import com.charlieworld.bookbug.repository.HistoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryServiceTests {

    @MockBean
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryService historyService;

    @Test
    public void findByUserIdTest() {
        Long userId = 1L;
        String query = "query";

        List<History> histories = new ArrayList<>();
        History history = History.builder().queryString(query).userId(userId).build();
        histories.add(history);

        UserHistory userHistory = UserHistory
                .builder()
                .keyword(history.getQueryString())
                .historyDateTime(history.getCreatedAt())
                .build();

        List<UserHistory> userHistories = new ArrayList<>();
        userHistories.add(userHistory);

        given(historyRepository.findByUserIdOrderByCreatedAtDesc(userId))
                .willReturn(histories);

        assertEquals(
                historyService.findByUserId(userId),
                userHistories
        );
    }

}
