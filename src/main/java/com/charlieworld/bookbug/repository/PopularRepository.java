package com.charlieworld.bookbug.repository;

import com.charlieworld.bookbug.entity.Popular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopularRepository extends JpaRepository<Popular, Long> {
    List<Popular> findTop10ByOrderByCountDesc();
}
