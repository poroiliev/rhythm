package com.rhythm.usercrossings.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class CommonBeans {

    @Bean
    public RestTemplate restTemplate() {
        return new ThreadSafeRestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    private static class ThreadSafeRestTemplate extends RestTemplate {
        @Override
        public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
            throw new UnsupportedOperationException();
        }
    }
}
