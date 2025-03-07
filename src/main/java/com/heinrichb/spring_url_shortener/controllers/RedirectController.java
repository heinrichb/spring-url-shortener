package com.heinrichb.spring_url_shortener.controllers;

import com.heinrichb.spring_url_shortener.models.UrlMapping;
import com.heinrichb.spring_url_shortener.services.UrlShortenerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class RedirectController {

    private final UrlShortenerService urlShortenerService;

    public RedirectController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/{shortUrl:[a-zA-Z0-9_-]+}")
    public String redirect(@PathVariable String shortUrl, Model model) {
        Optional<UrlMapping> urlMapping = urlShortenerService.findByShortUrl(shortUrl);

        if (urlMapping.isPresent()) {
            return "redirect:" + urlMapping.get().getOriginalUrl();
        }

        // Return the custom error page instead of JSON
        model.addAttribute("shortUrl", shortUrl);
        return "error";
    }
}
