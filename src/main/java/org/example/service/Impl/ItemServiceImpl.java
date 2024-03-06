package org.example.service.Impl;

import lombok.RequiredArgsConstructor;

import org.example.config.oauth2.PrincipalDetails;
import org.example.model.dto.shop.ItemDto;
import org.example.model.entity.shop.ImageFile;
import org.example.model.entity.shop.Item;
import org.example.repository.ImageFileRepository;
import org.example.repository.ItemRepository;
import org.example.service.ItemService;
import org.example.util.exception.CustomException;
import org.example.util.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ImageFileRepository imageFileRepository;

    @Value("${custom.path.image_upload}")
    private String imagePath;
    @Override
    public void deleteItem(int itemId, PrincipalDetails principalDetails) {
        Item response = itemRepository.getItemById(itemId);
        if (response == null) {
            new CustomException(ErrorCode.ID_NOT_FOUND);
        }
        if (!response.getSeller().equals(principalDetails.getUsername())) {
            new CustomException(ErrorCode.DELETE_ACCESS_DENIED);
        }
        itemRepository.deleteItem(itemId);
    }

    @Override
    public Item getItemById(int itemId) {
        return itemRepository.getItemById(itemId);
    }

    @Override
    public void editItem(int itemId, ItemDto.Request itemDto, List<MultipartFile> plusFile, List<String> removeFile, PrincipalDetails principalDetails) {
        Item response = itemRepository.getItemById(itemId);
        if (response == null) {
            new CustomException(ErrorCode.ID_NOT_FOUND);
        }

        if (!response.getSeller().equals(principalDetails.getUsername())) {
            new CustomException(ErrorCode.EDIT_ACCESS_DENIED);
        }

        Item item = Item.toEntity(itemId, itemDto);
        itemRepository.editItem(item);

        if (plusFile != null) {
            saveFile(itemId ,plusFile);
        }
        if (removeFile != null) {
            deleteFile(removeFile);
        }
    }
    private void saveFile(int itemId, List<MultipartFile> plusFile) {
        for (MultipartFile file : plusFile) {
            UUID uuid = UUID.randomUUID();
            String filename = uuid + "_" + file.getOriginalFilename();
            Path path = Paths.get(imagePath + File.separator + filename).toAbsolutePath();


            // 실제 저장되는 코드
            try {
                file.transferTo(path.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ImageFile imageFile = ImageFile.builder()
                    .itemId(itemId)
                    .imgFilename(filename)
                    .imgFilepath(imagePath)
                    .build();
            imageFileRepository.saveFile(imageFile);
        }
    }
    private void deleteFile(List<String> removeFile) {
        for (String file : removeFile) {
            String filename = URLDecoder.decode(file, StandardCharsets.UTF_8);
            imageFileRepository.removeFile(filename);
        }
    }

    @Override
    public void setHits() {
        itemRepository.setHits();
    }

    @Override
    public void setLikes(int likes) {
        itemRepository.setLikes(likes);
    }

    @Override
    public void setStock(int stock) {
        itemRepository.setStock(stock);
    }
}
