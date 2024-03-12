package org.example.model.dto.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.shop.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageFileDto {
    @Getter
    @Setter
    public static class Response {
        private String filepath;
        private String filename;
    }
    public static ImageFileDto.Response toImageDtoResponse(ImageFile imageFile){
        ImageFileDto.Response response = new Response();
        response.filename = imageFile.getImgFilename();
        response.filepath = "/static/img/" + response.filename;
        return response;
    }
}
