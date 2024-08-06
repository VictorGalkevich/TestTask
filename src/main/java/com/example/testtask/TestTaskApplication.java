package com.example.testtask;

import com.example.testtask.config.TranslationEndpointConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TranslationEndpointConfig.class)
public class TestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

}
