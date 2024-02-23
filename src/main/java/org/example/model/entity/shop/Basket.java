package org.example.model.entity.shop;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Basket {
    private int basket_id;
    private int user_id;
    private int item_id;
    @Builder
    public Basket(int user_id, int item_id) {
        this.user_id = user_id;
        this.item_id = item_id;
    }
}
