package com.danozzo.resumeText.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class SummaryService {

    // The model to be used for summarization
    private static final String MODEL = "gpt-3.5-turbo";

    // The prompt to be prepended to the text to be summarized
    private static final String PROMPT = "Riassumi il seguente testo mantenendo la sua lingua originale. Non tradurre il riassunto in " +
            "italiano o in qualsiasi altra lingua. Usa esattamente la stessa lingua del testo fornito.:\n";

    // Inject the WebClient bean
    private final WebClient webClient;

    // Inject the WebClient bean
    public SummaryService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Send a POST request to the OpenAI API to summarize the text
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