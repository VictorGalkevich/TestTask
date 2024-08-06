package com.example.testtask.controller;

import com.example.testtask.dto.TranslationRequest;
import com.example.testtask.dto.TranslationResponse;
import com.example.testtask.service.TranslationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/translation")
@RequiredArgsConstructor
public class TranslationController {
    private final TranslationService translationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> translate(@RequestBody TranslationRequest request, HttpServletRequest httpRequest) {
        return ResponseEntity.ok(translationService.translate(request, httpRequest.getRemoteAddr()));
    }
}
