package com.hussain.visaapp.service;

import com.hussain.visaapp.model.VisaHelloWorldResponse;
import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class VisaApiClient implements Supplier<String> {

  @Value("${project.url}")
  private String projectUrl;

  private final RestTemplate visaApiRestTemplate;

  public VisaApiClient(@NotNull final RestTemplate visaApiRestTemplate) {
    this.visaApiRestTemplate = visaApiRestTemplate;
  }

  @Override
  public String get() {
    final var visaApiResponse = visaApiRestTemplate.getForEntity(projectUrl,
        VisaHelloWorldResponse.class);

    return visaApiResponse.getStatusCode() == HttpStatus.OK ? Objects.requireNonNull(
        visaApiResponse.getBody()).getMessage()
        : null;
  }
}
