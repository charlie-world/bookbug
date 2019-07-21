package com.charlieworld.bookbug.repository;

import com.charlieworld.bookbug.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserIdOrderByCreatedAtDesc(Long userId);
}
