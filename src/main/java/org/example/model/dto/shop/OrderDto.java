package org.example.model.dto.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.User;
import org.example.model.entity.shop.Item;
import org.example.model.enums.Payment;

@Getter
@Setter
public class OrderDto {
    private int order_id;
    private int user_id;
    private int item_id;
    private int amount;
    private String address;
    private String payment;
}
