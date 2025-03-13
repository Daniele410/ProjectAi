package com.danozzo.resumeText.service;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SummaryServiceTest {

    private MockWebServer mockWebServer;
    private SummaryService summaryService;

    @BeforeEach
    void setUp() throws IOException {
        // ✅ Avviamo un server mock che simulerà OpenAI
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // ✅ Creiamo un WebClient reale che punta al server mock
        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer fake-api-key")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        // ✅ Inizializziamo il servizio con WebClient
        summaryService = new SummaryService(webClient);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void summarizeText_ShouldReturnSummary_WhenTextIsValid() throws Exception {
        //Given
        String fakeJsonResponse = "{ \"choices\": [{ \"message\": { \"content\": \"This is a summarized text.\" } }] }";

        mockWebServer.enqueue(new MockResponse()
                .setBody(fakeJsonResponse)
                .addHeader("Content-Type", "application/json"));

        //When
        String result = summaryService.summarizeText("This is a test text.");

        //Then
        assertEquals("This is a summarized text.", result);
    }
}
