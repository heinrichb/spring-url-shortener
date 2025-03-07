package com.heinrichb.spring_url_shortener.controllers;

import com.heinrichb.spring_url_shortener.models.UrlMapping;
import com.heinrichb.spring_url_shortener.services.UrlShortenerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.logging.Logger;

@Controller
@Order(1)
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class.getName());
    private final UrlShortenerService service;

    public HomeController(UrlShortenerService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        logger.info("🏠 HomeController handling request for `/`");

        List<UrlMapping> urlMappings = service.getAllUrls();
        model.addAttribute("urls", urlMappings);
        return "home";
    }
}
