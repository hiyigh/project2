package org.example.model.dto.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.shop.Item;

@Getter
@Setter
public class ItemDto {
    private int item_id;
    private int item_category_id;
    private String item_title;
    private String item_explain;
    private int item_price;
    private int item_stock;
    private int item_hits;
    private int item_discount;

    public static ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItem_id(item.getItem_id());
        itemDto.setItem_category_id(item.getItem_category_id());
        itemDto.setItem_title(item.getItem_title());
        itemDto.setItem_explain(item.getItem_explain());
        itemDto.setItem_price(item.getItem_price());
        itemDto.setItem_stock(item.getItem_stock());
        itemDto.setItem_hits(item.getItem_hits());
        itemDto.setItem_discount(item.getItem_discount());
        return itemDto;
    }
}
