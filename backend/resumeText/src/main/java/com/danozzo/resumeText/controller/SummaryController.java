package com.danozzo.resumeText.controller;

import com.danozzo.resumeText.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class SummaryController {

    private SummaryService summaryService;

    @Autowired
    public SummaryService getSummaryService(SummaryService summaryService) {
        return summaryService;
    }

    @PostMapping("/summarize")
    public Map<String, String> summarize(@RequestBody Map<String,String> request){
        String text = request.get("text");
        String summary = summaryService.summarizeText(text);
        return Collections.singletonMap("summary", summary);
    }
}
