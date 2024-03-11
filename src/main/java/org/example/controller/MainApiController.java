package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.shop.ImageFileDto;
import org.example.repository.ImageFileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainApiController {
    private final ImageFileRepository imageFileRepository;

    @GetMapping("/api/home")
    public ResponseEntity<Object> home() {
        Map<String, Object> map = new HashMap<>();
        List<ImageFileDto.Response> imageFile = imageFileRepository.getMainImage();
        map.put("imageFile", imageFile);
        return ResponseEntity.ok(imageFile);
    }
}
