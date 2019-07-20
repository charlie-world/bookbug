package com.charlieworld.bookbug.repository;

import com.charlieworld.bookbug.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    public Optional<UserToken> findByUserId(Long userId);
}
