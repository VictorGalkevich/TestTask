package com.example.testtask.dto;

public record TranslationRequest(
   String text,
   String reqLanguage,
   String respLanguage
) {
}
