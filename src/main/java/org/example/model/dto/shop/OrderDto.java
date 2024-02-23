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
    private User user;
    private Item item;
    private int amount;
    private String address;
    private Payment payment;
    public void allocPayment(String payment) {
        if (payment.equals("체크/신용카드")) {
            this.payment = Payment.CARD;
        } else if (payment.equals("휴대폰결제")) {
            this.payment = Payment.MOBILE;
        } else if (payment.equals("무통장입금")) {
            this.payment = Payment.ACCOUNT;
        }
    }
}
