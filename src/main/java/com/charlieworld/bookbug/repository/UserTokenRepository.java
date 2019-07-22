package com.charlieworld.bookbug.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserTokenRepository {

    private static String CACHE_KEY = "token";

    @Autowired
    private CacheManager cacheManager;

    public void save(Long userId, String token) {
        Cache cache = cacheManager.getCache(CACHE_KEY);
        cache.put(token, userId);
    }

    public Optional<Long> get(String token) {
        Cache cache = cacheManager.getCache(CACHE_KEY);
        Optional<Long> result;
        try {
            result = Optional.ofNullable(cache.get(token, Long.class));
        } catch (NullPointerException e) {
            result = Optional.empty();
        }
        return result;
    }
}
