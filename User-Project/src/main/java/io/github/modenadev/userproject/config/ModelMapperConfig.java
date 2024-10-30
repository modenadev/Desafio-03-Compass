package io.github.modenadev.userproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    // Config //
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}


