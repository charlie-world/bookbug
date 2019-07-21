package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.UserHistory;
import com.charlieworld.bookbug.entity.History;
import com.charlieworld.bookbug.entity.Query;
import com.charlieworld.bookbug.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public List<UserHistory> getUserHistory(Long userId) {
        List<UserHistory> userHistories = new ArrayList<>();
        List<History> histories = historyRepository.findByUserIdOrderByCreatedAtDesc(userId);
        for (History history : histories) {
            UserHistory userHistory = UserHistory
                    .builder()
                    .keyword(history.getQuery().getQueryString())
                    .historyDateTime(history.getCreatedAt())
                    .build();
            userHistories.add(userHistory);
        }
        return userHistories;
    }

    public void insertHistory(Long userId, Query query) {
        historyRepository.save(History.builder().query(query).userId(userId).build());
    }
}
