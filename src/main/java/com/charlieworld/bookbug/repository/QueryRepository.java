package com.charlieworld.bookbug.repository;

import com.charlieworld.bookbug.entity.Query;
import com.charlieworld.bookbug.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
    Optional<Query> findByQueryStringAndPageAndTarget(String queryString, int page, Target target);
}
