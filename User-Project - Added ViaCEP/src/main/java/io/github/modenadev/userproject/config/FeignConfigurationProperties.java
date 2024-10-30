package io.github.modenadev.userproject.config;

import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfigurationProperties {
    @Bean
    public FeignClientProperties feignClientProperties() {
        return new FeignClientProperties();
    }
}