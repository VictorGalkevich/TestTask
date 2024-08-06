package com.example.testtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {

    @Bean
    public ExecutorService threadPoolTaskExecutor() {
        return Executors.newFixedThreadPool(10);
    }
}
