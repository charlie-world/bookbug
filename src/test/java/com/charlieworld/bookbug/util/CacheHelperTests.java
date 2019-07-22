package com.charlieworld.bookbug.util;

import org.junit.Test;

public class CacheHelperTests {

    @Test
    public void cacheKeyTest() {
        String queryString = "abcdedf";
        int page = 1;

        String expect = "abcdedf-1";

        assert CacheHelper.cacheKey(queryString, page).equals(expect);
    }
}
