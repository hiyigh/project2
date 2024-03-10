package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.example.config.oauth2.PrincipalDetails;
import org.example.model.dto.shop.ItemDto;
import org.example.model.entity.shop.Item;
import org.example.model.entity.shop.ImageFile;
import org.example.service.ImageFileService;
import org.example.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop/api")
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;
    private final ImageFileService imageFileService;

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable int itemId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        itemService.deleteItem(itemId, principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/detail/{itemId}")
    public ResponseEntity<?> detailItem(@PathVariable int itemId) {
        Map<String, Object> response = new HashMap<>();

        Item item =  itemService.getItemById(itemId);
        List<ImageFile> itemFile =  imageFileService.getItemFileById(itemId);

        response.put("item",item);
        response.put("itemFile", itemFile);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/edit/{itemId}")
    public ResponseEntity<?> editItem(@PathVariable int itemId,
                                   @RequestPart(value = "itemRequest")ItemDto.Request itemDto,
                                   @RequestPart(value = "plusFile") List<MultipartFile> plusFile,
                                   @RequestPart(value = "removeFile")List<String> rmFile,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {
        itemService.editItem(itemId, itemDto, plusFile, rmFile, principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}
