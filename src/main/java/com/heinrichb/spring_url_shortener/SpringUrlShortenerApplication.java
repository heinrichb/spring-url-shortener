package com.heinrichb.spring_url_shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.heinrichb.spring_url_shortener.controllers",
    "com.heinrichb.spring_url_shortener.services",
    "com.heinrichb.spring_url_shortener.repositories"
})
public class SpringUrlShortenerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringUrlShortenerApplication.class, args);
    }
}
