package org.example.model.dto.shop;

import org.example.model.entity.shop.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageFileDto {
    public static class Response {
        private String filename;
    }
    public static ImageFileDto.Response toImageDtoResponse(ImageFile imageFile){
        ImageFileDto.Response response = new Response();
        response.filename = imageFile.getImgFilename();
        return response;
    }
}
