package com.danozzo.resumeText.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyConfig {

    @Value("${APIKEY}")
    private String apiKey;

    public String getApiKey(){
        return apiKey;
    }
}
