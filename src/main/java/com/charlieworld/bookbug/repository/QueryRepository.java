package com.charlieworld.bookbug.repository;

import com.charlieworld.bookbug.entity.Query;
import com.charlieworld.bookbug.entity.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
    Optional<Query> findByQueryStringAndPageAndTargetType(String queryString, int page, TargetType targetType);
}
