package io.github.modenadev.userproject.config;

import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public FeignContext feignContext() {
        return new FeignContext();
    }
}