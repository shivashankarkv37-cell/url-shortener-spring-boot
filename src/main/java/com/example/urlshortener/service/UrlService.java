package com.example.urlshortener.service;

import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;

public interface UrlService {
    UrlResponse createUrl(UrlRequest request);
}
