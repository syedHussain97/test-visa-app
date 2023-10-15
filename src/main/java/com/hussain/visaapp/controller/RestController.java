package com.hussain.visaapp.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Value("${project.url}")
    private String welcomeUrl;

    private final RestTemplate visaApiRestTemplate;

    public RestController(@NotNull RestTemplate visaApiRestTemplate) {
        this.visaApiRestTemplate = visaApiRestTemplate;
    }

    @GetMapping("/welcomeclient")
    public String greetMessage() {

        return visaApiRestTemplate.getForObject(welcomeUrl, String.class);
    }
}
