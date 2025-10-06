package org.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class MovieTrackerController {

    @GetMapping("/api/movie")
    public Map<String, String> movie() {
        return Map.of("value", "Inception");
    }

}
