package org.example.model.entity.shop;

import lombok.Builder;
import lombok.Getter;
import org.example.model.dto.shop.BasketDto;

@Getter
public class Basket {
    private int basketId;
    private String userName;
    private int itemId;
    @Builder
    public Basket(String userName, int itemId) {
        this.userName = userName;
        this.itemId = itemId;
    }

}
