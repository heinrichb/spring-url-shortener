package com.heinrichb.spring_url_shortener.services;

import com.heinrichb.spring_url_shortener.models.UrlMapping;
import com.heinrichb.spring_url_shortener.repositories.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository repository;

    public UrlShortenerService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    // Shorten a URL and save it
    public UrlMapping shortenUrl(String originalUrl) {
        Optional<UrlMapping> existingMapping = repository.findByOriginalUrl(originalUrl);
        if (existingMapping.isPresent()) {
            return existingMapping.get();
        }

        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping(originalUrl, shortUrl);
        return repository.save(urlMapping);
    }

    // Find original URL by short URL
    public Optional<UrlMapping> findByShortUrl(String shortUrl) {
        return repository.findByShortUrl(shortUrl);
    }

    // Fetch all shortened URLs
    public List<UrlMapping> getAllUrls() {
        return repository.findAll();
    }

    // Generate a short unique identifier
    private String generateShortUrl() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
