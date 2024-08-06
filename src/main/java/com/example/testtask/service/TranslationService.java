package com.example.testtask.service;

import com.example.testtask.config.TranslationEndpointConfig;
import com.example.testtask.dto.TranslationRequest;
import com.example.testtask.dto.TranslationResponse;
import com.example.testtask.repository.TranslationRepository;
import com.example.testtask.uri.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.springframework.http.HttpMethod.*;

@Service
@RequiredArgsConstructor
public class TranslationService {
    private final TranslationRepository translationRepository;
    private final TranslationEndpointConfig config;
    private final RestTemplate restTemplate;
    private final UriCreator uriCreator;
    private final ExecutorService executorService;
    private final Lock lock = new ReentrantLock();
    private AtomicReferenceArray<String> res;
    private String[] words;

    public String translate(TranslationRequest request, String ipAddr) {
        Map<String, String> configHeaders = config.headers();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        for (String headerName : configHeaders.keySet()) {
            headers.set(headerName, configHeaders.get(headerName));
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);

        words = request.text().split(" ");

        executeTranslation(words, entity, request);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < res.length(); i++) {
            result.append(res.get(i)).append(" ");
        }
        translationRepository.save(request.text(), result.toString(), ipAddr);

        return result.toString();
    }

    public void executeTranslation(
            String[] words,
            HttpEntity<String> entity,
            TranslationRequest req) {
        res = new AtomicReferenceArray<String>(words.length);
        for (int i = 0; i < words.length; i++) {
            int pos = i;
            executorService.execute(() -> process(entity, req, pos));
        }
    }

    public void process(HttpEntity<String> entity,
                        TranslationRequest req,
                        int i) {
        String resp;
        String respLan = req.respLanguage();
        String reqLan = req.reqLanguage();
        TranslationRequest reqForWord = new TranslationRequest(words[i], reqLan, respLan);
        if (ServiceCache.get(reqForWord) != null) {
            resp = ServiceCache.get(reqForWord);
        } else {
            resp = restTemplate.exchange(
                    create(reqLan, respLan, words[i]),
                    GET, entity,
                    TranslationResponse.class).getBody().responseData().translatedText();
                ServiceCache.put(reqForWord, resp);
        }
        while (!lock.tryLock()) {
            res.set(i, resp);
        }
        lock.unlock();
    }

    public String create(String from, String to, String text) {
        return uriCreator.create(
                from,
                to,
                text);
    }
}
