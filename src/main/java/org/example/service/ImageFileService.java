package org.example.service;

import org.example.model.entity.board.PostFile;
import org.example.model.entity.shop.ImageFile;

import java.util.List;

public interface ImageFileService {
    void savePostFile(PostFile postFile);
    void saveItemFile(ImageFile itemFile);
    List<PostFile> getPostFileById(int postId);
    List<ImageFile> getItemFileById(int itemId);
}
