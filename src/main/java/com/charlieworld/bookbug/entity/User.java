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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="real_id" , unique=true)
    private String realId;

    private String encryptedPassword;
    private String encryptKey;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public User(String realId, String encryptedPassword, String encryptKey) {
        this.realId = realId;
        this.encryptedPassword = encryptedPassword;
        this.encryptKey = encryptKey;
    }
}
