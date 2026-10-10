package com.example.urlshortener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String originalUrl;
    @Column(unique = true,nullable = false)
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

}
