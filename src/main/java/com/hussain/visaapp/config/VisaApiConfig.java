package com.hussain.visaapp.config;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class VisaApiConfig {

    @Value("${key.store}")
    private String keyStore;

    @Value("${key.store.password}")
    private String keyStorePassword;

    /**
     * @param restTemplateBuilder
     * @return RestTemplate create this template for all visa api calls
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws CertificateException
     * @throws IOException
     * @throws UnrecoverableKeyException
     */
    @Bean
    public RestTemplate visaApiRestTemplate(@NotNull final RestTemplateBuilder restTemplateBuilder,
                                            @NotNull final ClientHttpRequestInterceptor visaRestCallInterceptor)
            throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException,
            IOException, UnrecoverableKeyException {

        final SSLContext sslContext = new SSLContextBuilder()
                .loadKeyMaterial(new File(keyStore), keyStorePassword.toCharArray(),
                        keyStorePassword.toCharArray())
                .loadTrustMaterial(new File(keyStore), keyStorePassword.toCharArray())
                .build();

        final SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new String[]{"TLSv1.2"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        final CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslSocketFactory).build();

        final RestTemplate restTemplate = restTemplateBuilder.build();
        final ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(requestFactory);

        List<ClientHttpRequestInterceptor> interceptorsToSet = new ArrayList<>();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (!CollectionUtils.isEmpty(interceptors)) {
            Collections.copy(interceptorsToSet, interceptors);
        }
        interceptorsToSet.add(visaRestCallInterceptor);
        restTemplate.setInterceptors(interceptorsToSet);
        return restTemplate;
    }

}
