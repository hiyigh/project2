package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.shop.ItemDto;
import org.example.model.entity.shop.Item;
import org.example.model.entity.shop.ImageFile;
import org.example.service.ImageFileService;
import org.example.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity deleteItem(@PathVariable int itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/detail/{itemId}")
    public ResponseEntity detailItem(@PathVariable int itemId) {
        Map<String, Object> response = new HashMap<>();

        Item item =  itemService.getItemById(itemId);
        List<ImageFile> itemFile =  imageFileService.getItemFileById(itemId);

        response.put("item",item);
        response.put("itemFile", itemFile);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/edit/{itemId}")
    public ResponseEntity editItem(@PathVariable int itemId,
                                   @RequestPart(value = "itemRequest")ItemDto.Request itemDto,
                                   @RequestPart(value = "itemImageFile") ImageFile itemFile) {
        itemService.editItem(itemId, itemDto);
        imageFileService.saveItemFile(itemFile);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

}
