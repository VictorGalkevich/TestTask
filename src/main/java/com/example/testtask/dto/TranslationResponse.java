package com.example.testtask.dto;

public record TranslationResponse(
        ResponseData responseData
) {
    public record ResponseData(
            String translatedText,
            Double match
    ){

}
}
