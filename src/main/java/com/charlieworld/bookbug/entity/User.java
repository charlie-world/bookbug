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
@Table(indexes = {@Index(name = "idx_real_id", columnList = "realId")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "realId", unique = true)
    private String realId;

    private String encryptedPassword;
    private String encryptKey;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public User(String realId, String encryptedPassword, String encryptKey) {
        this.realId = realId;
        this.encryptedPassword = encryptedPassword;
        this.encryptKey = encryptKey;
    }
}
