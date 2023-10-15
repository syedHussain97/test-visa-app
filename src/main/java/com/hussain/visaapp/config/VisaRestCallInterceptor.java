package com.hussain.visaapp.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class VisaRestCallInterceptor implements ClientHttpRequestInterceptor {

    @Value("${project.username}")
    private String projectUserName;

    @Value("${project.password}")
    private String projectPassword;

    @Override
    public ClientHttpResponse intercept(@NotNull HttpRequest request,
                                        byte @NotNull [] body,
                                        @NotNull ClientHttpRequestExecution execution) throws IOException {

        final HttpHeaders headers = request.getHeaders();
        headers.setBasicAuth(projectUserName, projectPassword);
        return execution.execute(request, body);
    }
}