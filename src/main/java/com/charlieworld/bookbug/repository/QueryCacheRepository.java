package com.charlieworld.bookbug.repository;

import com.charlieworld.bookbug.dto.BookList;
import com.charlieworld.bookbug.dto.BookSimple;
import com.charlieworld.bookbug.entity.Book;
import com.charlieworld.bookbug.entity.TargetType;
import com.charlieworld.bookbug.util.BookHelper;
import com.charlieworld.bookbug.util.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueryCacheRepository {

    @Autowired
    private CacheManager cacheManager;

    public BookList getCachedBookList(TargetType targetType, String queryString, int page) {
        Cache cache = cacheManager.getCache(targetType.getValue());
        String cacheKey = CacheHelper.cacheKey(queryString, page);
        BookList cachedBooks;
        try {
            cachedBooks = cache.get(cacheKey, BookList.class);
        } catch (NullPointerException e) {
            cachedBooks = null;
        }
        return cachedBooks;
    }

    public BookList put(TargetType targetType, String queryString, int page, boolean isEnd, List<Book> books) {
        Cache cache = cacheManager.getCache(targetType.getValue());
        String cacheKey = CacheHelper.cacheKey(queryString, page);
        List<BookSimple> bookSimples = BookHelper.toBookSimple(books);
        BookList bookList = BookList.builder().books(bookSimples).page(page).isEnd(isEnd).build();
        cache.put(cacheKey, bookList);
        return bookList;
    }
}
