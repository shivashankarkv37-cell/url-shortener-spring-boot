package com.example.urlshortener.service;

import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.time.LocalDateTime;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public UrlResponse createUrl(UrlRequest request) {
        String originalUrl = request.getOriginalUrl();
        Url url = new Url();

        url.setOriginalUrl(originalUrl);
        url.setShortCode(generateShortCode());
        url.setCreatedAt(LocalDateTime.now());

        Url savedUrl = urlRepository.save(url);

        UrlResponse response = new UrlResponse();

        response.setId(savedUrl.getId());
        response.setOriginalUrl(savedUrl.getOriginalUrl());
        response.setShortCode(savedUrl.getShortCode());
        response.setShortUrl(
                "http://localhost:8080/" + savedUrl.getShortCode()
        );
        response.setCreatedAt(savedUrl.getCreatedAt());

        return response;
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Short URL not found"));
    }

    private String generateShortCode() {

        return UUID.randomUUID()
                .toString()
                .substring(0, 6);
    }
}
