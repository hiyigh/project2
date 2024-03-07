package org.example.service;

import org.example.config.oauth2.PrincipalDetails;
import org.example.model.dto.shop.ItemDto;
import org.example.model.entity.shop.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    void deleteItem(int itemId, PrincipalDetails principalDetails);

    Item getItemById(int itemId);

    void editItem(int itemId, ItemDto.Request itemDto, List<MultipartFile> plusFile, List<String> removeFile, PrincipalDetails principalDetails);
    void setHits();
    void setLikes(int likes);
    void setStock(int stock);
}
