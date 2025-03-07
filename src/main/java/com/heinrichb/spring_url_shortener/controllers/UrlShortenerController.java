package com.heinrichb.spring_url_shortener.controllers;

import com.heinrichb.spring_url_shortener.models.UrlMapping;
import com.heinrichb.spring_url_shortener.services.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    private final UrlShortenerService service;

    public UrlShortenerController(UrlShortenerService service) {
        this.service = service;
    }

    // Shorten a URL
    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlMapping urlMapping) {
        if (urlMapping.getOriginalUrl() == null || urlMapping.getOriginalUrl().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"No URL provided\"}");
        }

        UrlMapping savedMapping = service.shortenUrl(urlMapping.getOriginalUrl());
        return ResponseEntity.ok(savedMapping);
    }

    // Fetch all shortened URLs
    @GetMapping("/all")
    public ResponseEntity<List<UrlMapping>> getAllUrls() {
        return ResponseEntity.ok(service.getAllUrls());
    }
}
