package com.charlieworld.bookbug.repository;

import com.charlieworld.bookbug.entity.Popular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PopularRepository extends JpaRepository<Popular, Long> {
    List<Popular> findTop10ByOrderByCountDesc();

    Optional<Popular> findByQueryString(String queryString);
}
