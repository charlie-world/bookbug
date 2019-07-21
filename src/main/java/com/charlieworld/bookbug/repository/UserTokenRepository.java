package com.charlieworld.bookbug.repository;

import com.charlieworld.bookbug.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByUserId(Long userId);

    Optional<UserToken> findByToken(String token);
}
