package com.hussain.visaapp.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;


@Component
public class VisaApiClient implements Supplier<String> {

    @Value("${project.url}")
    private String projectUrl;

    private final RestTemplate visaApiRestTemplate;

    public VisaApiClient(@NotNull RestTemplate visaApiRestTemplate) {
        this.visaApiRestTemplate = visaApiRestTemplate;
    }

    @Override
    public String get() {
        return visaApiRestTemplate.getForObject(projectUrl, String.class);
    }
}
