package com.hussain.visaapp.controller;

import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class VisaAppRestController {

  private final Supplier<String> visaApiClient;

  public VisaAppRestController(@NotNull final Supplier<String> visaApiClient) {
    this.visaApiClient = visaApiClient;
  }


  @GetMapping(value = "/welcomeclient", produces = MediaType.APPLICATION_JSON_VALUE)
  public String greetMessage() {
    return visaApiClient.get();
  }
}
