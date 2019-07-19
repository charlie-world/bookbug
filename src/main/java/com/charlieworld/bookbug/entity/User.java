package com.charlieworld.bookbug.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    private String realId;
    private String encryptedPassword;
    private String encryptKey;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
