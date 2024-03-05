package org.example.model.entity.shop;

import lombok.Builder;
import lombok.Getter;
import org.example.model.enums.OrderStatus;
import org.example.model.enums.Payment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order {
    private int orderId;
    private String userName;
    private List<Integer> itemId;
    private int addressId;
    private String payment;
    private String status;
    private LocalDateTime orderTime = LocalDateTime.now();

    @Builder
    public Order(String userName, List<Integer> itemId, int addressId, String payment, OrderStatus status) {
        this.userName = userName;
        this.itemId = itemId;
        this.addressId = addressId;
        this.payment = payment;
        this.status = status.toString();
    }
}
