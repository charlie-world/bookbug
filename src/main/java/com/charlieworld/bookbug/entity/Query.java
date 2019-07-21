package com.charlieworld.bookbug.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(name = "idx_query_string", columnList = "queryString")})
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queryId;

    private String queryString;
    private int page;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private List<Book> books;

    @Enumerated(EnumType.STRING)
    private Target target;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Query(String queryString, int page, List<Book> books, Target target) {
        this.queryString = queryString;
        this.page = page;
        this.books = books;
        this.target = target;
    }
}
