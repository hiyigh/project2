package org.example.model.entity.shop;

import lombok.Builder;
import lombok.Getter;
import org.example.model.enums.Payment;

@Getter
public class Order {
    private int order_id;
    private int user_id;
    private int item_id;
    private int amount;
    private String address;
    private String payment;

    @Builder
    public Order(int user_id, int item_id,int amount ,String address, String payment){
        this.user_id = user_id;
        this.item_id = item_id;
        this.amount = amount;
        this.address = address;
        this.payment = payment;
    }
    public void setAmount(int num) {
        if(this.amount + num <= 0) {
            this.amount = 0;
        } else {
            this.amount += num;
        }
    }
}
