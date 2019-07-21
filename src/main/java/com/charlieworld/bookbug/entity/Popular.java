package com.charlieworld.bookbug.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(name = "idx_count", columnList = "count")})
public class Popular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long popularId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queryId")
    private Query query;

    private Long count;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Popular(Long popularId, Query query, Long count) {
        this.popularId = popularId;
        this.query = query;
        this.count = count;
    }
}
