package org.example.service;

import org.example.model.entity.shop.ImageFile;

import java.util.List;

public interface ImageFileService {
    void saveFile(ImageFile itemFile);
    void removeFile(String filename);
    List<ImageFile> getItemFileById(int itemId);
}
