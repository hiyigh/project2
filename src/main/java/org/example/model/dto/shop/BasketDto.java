package org.example.model.dto.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.example.model.entity.shop.Basket;
import org.example.model.entity.shop.Item;

import java.util.List;

public class BasketDto {
    @Getter
    @Setter
    public static class Request {
        private String userName;
        private int itemId;
    }
    @Getter
    @Setter
    public static class Response {
        private int basketId;
        private String userName;
        private List<ItemDto.Response> itemList;
    }
    public static BasketDto.Response toBasketDtoResponse(Basket basket ,List<ItemDto.Response> itemList) {
        BasketDto.Response response = new Response();
        response.basketId = basket.getBasketId();
        response.userName = basket.getUserName();
        response.itemList = itemList;
        return response;
    }
}
