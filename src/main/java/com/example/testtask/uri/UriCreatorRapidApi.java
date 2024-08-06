package com.example.testtask.uri;

import com.example.testtask.config.TranslationEndpointConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UriCreatorRapidApi implements UriCreator {
    private final TranslationEndpointConfig config;

    @Override
    public String create(String from, String to, String query) {
        return config.uri().toString() + "/get?" + "langpair=" + from + "|" + to
                + "&q=" + query
                + "&mt=1&onlyprivate=0&de=2vektor54@gmail.com";
    }
}
