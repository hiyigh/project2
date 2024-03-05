package org.example.model.dto.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.example.model.entity.shop.Item;
import org.example.model.entity.shop.ImageFile;

import java.util.List;
public class ItemDto {
    @Getter
    @Setter
    public static class Request {
        private int categoryId;
        private String title;
        private String explain;
        private List<ImageFile> itemFileList;
        private int price;
        private int stock;
        private int discount;
    }
    @Getter
    @Setter
    public static class Response {
        private int itemId;
        private String seller;
        private String title;
        private String explain;
        private List<ImageFile> imageFiles;
        private int price;
        private int hits;
        private int likes;
        private int stock;
        private int discount;
    }
    public static ItemDto.Response toItemDtoResponse(Item item,List<ImageFile> imageFiles) {
        ItemDto.Response response = new Response();
        response.itemId = item.getItemId();
        response.seller = item.getSeller();
        response.title = item.getTitle();
        response.explain = item.getExplain();
        response.imageFiles = imageFiles;
        response.price = item.getPrice();
        response.hits = item.getHits();
        response.likes = item.getLikes();
        response.stock = item.getStock();
        response.discount = item.getDiscount();
        return response;
    }
}
