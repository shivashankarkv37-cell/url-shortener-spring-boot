package com.example.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlResponse {
    private Long id;

    private String originalUrl;

    private String shortCode;

    private String shortUrl;

    private LocalDateTime createdAt;
}
