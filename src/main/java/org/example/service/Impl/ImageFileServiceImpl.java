package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.shop.ImageFile;
import org.example.repository.ImageFileRepository;
import org.example.service.ImageFileService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ImageFileServiceImpl implements ImageFileService {
    private final ImageFileRepository imageFileRepository;

    @Override
    public void saveFile(ImageFile itemFile) {
        imageFileRepository.saveFile(itemFile);
    }
    @Override
    public List<ImageFile> getItemFileById(int itemId) {
        return imageFileRepository.getItemFileById(itemId);
    }

    @Override
    public void removeFile(String filename) {
        imageFileRepository.removeFile(filename);
    }
}
