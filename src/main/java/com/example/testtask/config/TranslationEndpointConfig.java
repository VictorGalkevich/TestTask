package com.example.testtask.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.util.Map;

@ConfigurationProperties("translation.api")
public record TranslationEndpointConfig(
        URI uri,
        Map<String, String> cookies,
        Map<String, String> headers
) {
}
