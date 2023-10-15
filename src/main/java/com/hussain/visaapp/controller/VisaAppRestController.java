package com.hussain.visaapp.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.function.Supplier;

@org.springframework.web.bind.annotation.RestController
public class VisaAppRestController {

    private final Supplier<String> visaApiClient;

    public VisaAppRestController(@NotNull Supplier<String> visaApiClient) {
        this.visaApiClient = visaApiClient;
    }


    @GetMapping("/welcomeclient")
    public String greetMessage() {
        return visaApiClient.get();
    }
}
