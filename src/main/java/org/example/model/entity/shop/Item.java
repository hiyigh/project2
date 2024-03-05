package org.example.model.entity.shop;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.model.dto.shop.ItemDto;

@Getter
@NoArgsConstructor
public class Item {
    private int itemId;
    private int categoryId;
    private String title;
    private String explain;
    private String seller;

    private int price;
    private int stock;
    private int hits;
    private int likes;
    private int discount;
    @Builder
    public Item(int categoryId, String title, String explain, String seller, int price, int stock,
                int hits,int likes ,int discount) {
        this.categoryId = categoryId;
        this.title = title;
        this.explain = explain;
        this.seller = seller;
        this.price = price;
        this.stock = stock;
        this.hits = hits;
        this.likes = likes;
        this.discount = discount;
    }
}
