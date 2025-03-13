package com.danozzo.resumeText.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class SummaryService {

    private static final String MODEL = "gpt-3.5-turbo";
    private static final String PROMPT = "Riassumi brevemente questo testo:\n";
    private final WebClient webClient;

    // ✅ Usa @Value per ottenere la chiave API
    @Value("${API_KEY}")
    private String apiKey;

    // ✅ Riceve il WebClient configurato come bean da Spring
    public SummaryService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String summarizeText(String text) {
        Map<String, Object> requestBody = Map.of(
                "model", MODEL,
                "messages", List.of(Map.of("role", "user", "content", PROMPT + text)),
                "max_tokens", 500,
                "temperature", 0.5
        );

        return webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> response.get("choices").get(0).get("message").get("content").asText())
                .block();
    }
}