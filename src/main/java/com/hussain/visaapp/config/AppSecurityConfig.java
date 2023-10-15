package com.hussain.visaapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        // Permit all the requests, no points are protected at this point
        http.authorizeRequests().antMatchers("/*").permitAll();

        return http.build();
    }
}
