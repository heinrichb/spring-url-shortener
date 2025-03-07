package com.heinrichb.spring_url_shortener.repositories;

import com.heinrichb.spring_url_shortener.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByShortUrl(String shortUrl);
    Optional<UrlMapping> findByOriginalUrl(String originalUrl);
}
