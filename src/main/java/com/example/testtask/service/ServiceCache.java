package com.example.testtask.service;

import com.example.testtask.dto.TranslationRequest;

import java.util.concurrent.ConcurrentHashMap;

public class ServiceCache {
    private static ConcurrentHashMap<TranslationRequest, String> serviceMap = new ConcurrentHashMap<>();

    public static void put(TranslationRequest req, String resp) {
        serviceMap.put(req, resp);
    }

    public static String get(TranslationRequest req) {
        return serviceMap.get(req);
    }
}
