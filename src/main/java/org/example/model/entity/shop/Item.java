package org.example.model.entity.shop;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.model.dto.shop.ItemDto;

@Getter
@NoArgsConstructor
public class Item {
    private int item_id;
    private int item_category_id;
    private String item_title;
    private String item_explain;
    private int item_price;
    private int item_stock;
    private int item_hits;
    private int item_discount;
    @Builder
    public Item(int item_category_id, String item_title, String item_explain, int item_price,
                int item_stock, int item_hits, int item_discount) {
        this.item_category_id = item_category_id;
        this.item_title = item_title;
        this.item_explain =item_explain;
        this.item_price = item_price;
        this.item_stock = item_stock;
        this.item_hits = item_hits;
        this.item_discount = item_discount;
    }
    public void addStock(int add) {
        this.item_stock += add;
    }
    public void subStock(int sub) {
        this.item_stock -= sub;
    }
    public void addHits() {
        this.item_hits += 1;
    }
    public void setDiscount(int discount) {
        this.item_discount = discount;
    }
    public void updateExplain(String explain) {
        this.item_explain = explain;
    }
    public void updateTitle(String title) {
        this.item_title = title;
    }
}
