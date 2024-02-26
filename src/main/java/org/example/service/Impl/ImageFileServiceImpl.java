package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.board.PostFile;
import org.example.model.entity.shop.ItemFile;
import org.example.repository.ImageFileRepository;
import org.example.service.ImageFileService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ImageFileServiceImpl implements ImageFileService {
    private final ImageFileRepository imageFileRepository;

    @Override
    public void savePostFile(PostFile postFile) {
        imageFileRepository.savePostFile(postFile);
    }

    @Override
    public void saveItemFile(ItemFile itemFile) {
        imageFileRepository.saveItemFile(itemFile);
    }

    @Override
    public List<PostFile> getPostFileById(int postId) {
        return imageFileRepository.getPostFileById(postId);
    }

    @Override
    public List<ItemFile> getItemFileById(int itemId) {
        return imageFileRepository.getItemFileById(itemId);
    }
}
