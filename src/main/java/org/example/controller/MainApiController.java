package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.shop.ImageFileDto;
import org.example.repository.ImageFileRepository;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.PartHttpMessageWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Multipart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainApiController {
    private final ImageFileRepository imageFileRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/img/";
    @PostMapping("/home/api/saveImage")
    public ResponseEntity<?> saveImageFromHome(@RequestParam(value="image[]")MultipartFile[] files) {
        if(files.length == 0 || files == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("image files are null");
        }
        try {
//            File uploadDir = new File(UPLOAD_DIR);
//            if(!uploadDir.exists()){
//                uploadDir.mkdir();
//            }
            for (MultipartFile file : files) {
                String filename = file.getOriginalFilename();
                String filepath = UPLOAD_DIR + filename;
                Path path = Path.of(filepath);
                file.transferTo(path);
            }
        }catch(IOException e){
            e.printStackTrace();
            return ResponseEntity.ok().body("fail to save image");
        }
        return ResponseEntity.ok().body("save image");
    }
}
