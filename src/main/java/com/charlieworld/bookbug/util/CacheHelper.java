package com.charlieworld.bookbug.util;

public class CacheHelper {

    public static String cacheKey(String queryString, int page) {
        return String.format("%s-%d", queryString, page);
    }
}
