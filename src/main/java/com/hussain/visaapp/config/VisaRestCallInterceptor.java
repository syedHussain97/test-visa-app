package com.hussain.visaapp.config;

import java.io.IOException;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * Creating this interceptor to add basic auth to the requests to visa api
 */
@Component
public class VisaRestCallInterceptor implements ClientHttpRequestInterceptor {

  @Value("${project.username}")
  private String projectUserName;

  @Value("${project.password}")
  private String projectPassword;

  @Override
  public @NotNull ClientHttpResponse intercept(@NotNull final HttpRequest request,
      final byte @NotNull [] body,
      @NotNull final ClientHttpRequestExecution execution) throws IOException {

    final HttpHeaders headers = request.getHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    headers.setBasicAuth(projectUserName, projectPassword);
    return execution.execute(request, body);
  }
}
