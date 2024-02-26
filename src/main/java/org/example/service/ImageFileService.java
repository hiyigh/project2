package org.example.service;

import org.example.model.entity.board.PostFile;
import org.example.model.entity.shop.ItemFile;

import java.util.List;

public interface ImageFileService {
    void savePostFile(PostFile postFile);
    void saveItemFile(ItemFile itemFile);
    List<PostFile> getPostFileById(int postId);
    List<ItemFile> getItemFileById(int itemId);
}
