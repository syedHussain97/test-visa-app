package com.hussain.visaapp.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@org.springframework.web.bind.annotation.RestController
public class VisaAppRestController {

    @Value("${project.url}")
    private String projectUrl;

    private final RestTemplate visaApiRestTemplate;

    public VisaAppRestController(@NotNull RestTemplate visaApiRestTemplate) {
        this.visaApiRestTemplate = visaApiRestTemplate;
    }

    @GetMapping("/welcomeclient")
    public String greetMessage() {

        return visaApiRestTemplate.getForObject(projectUrl, String.class);
    }
}
