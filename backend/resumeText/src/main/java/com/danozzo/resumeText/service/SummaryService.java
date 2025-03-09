package com.danozzo.resumeText.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class SummaryService {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    @Value("${APIKEY}")
    private String APIKEY;
    private static final String MODEL = "gpt-3.5-turbo";

    private final WebClient webClient = WebClient.builder()
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + APIKEY)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public String summarizeText(String text) {
        String prompt = "Riassumi brevemente questo testo: " + text;

        Map<String, Object> requestBody = Map.of(
                "model", MODEL,
                "messages", List.of(Map.of("role", "user", "content", prompt)),
                "max_tokens", 150
        );

        String response = webClient.post()
                .uri(OPENAI_API_URL)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block()
                .get("choices").get(0).get("message").get("content").asText();
        return response;
    }

}
