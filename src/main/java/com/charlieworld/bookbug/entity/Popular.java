package com.charlieworld.bookbug.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {@Index(name = "idx_count", columnList = "count")})
public class Popular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long popularId;

    @Column(name = "queryString", unique = true)
    private String queryString;

    private Long count;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public Popular(Long popularId, String queryString, Long count) {
        this.popularId = popularId;
        this.queryString = queryString;
        this.count = count;
    }
}
