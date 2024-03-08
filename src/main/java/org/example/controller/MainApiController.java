package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainApiController {
    @GetMapping("/api/home")
    public ResponseEntity<?> home() {
        Map<String, Object> map = new HashMap<>();
        return
    }
}
