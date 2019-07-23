package com.charlieworld.bookbug.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String name;
    private String thumbnail;

    @Column(name = "contents", length = 1024)
    private String contents;

    @Column(name = "isbn", unique = true)
    private String isbn;

    private String authors;
    private String publisher;
    private LocalDateTime publishDatetime;
    private int price;
    private int salePrice;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public Book(
            String name,
            String thumbnail,
            String contents,
            String isbn,
            String authors,
            String publisher,
            LocalDateTime publishDatetime,
            int price,
            int salePrice
    ) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.contents = contents;
        this.isbn = isbn;
        this.authors = authors;
        this.publisher = publisher;
        this.publishDatetime = publishDatetime;
        this.price = price;
        this.salePrice = salePrice;
    }
}
