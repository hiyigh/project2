package org.example.model.dto.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.shop.Address;
import org.example.model.entity.shop.Order;
import org.example.model.enums.Payment;

import java.util.ArrayList;
import java.util.List;


public class OrderDto {
    @Getter
    @Setter
    public static class Request {
        // username
        private List<Integer> itemId;
        private Address address;
        private Payment payment;
    }
    @Getter
    public static class Response {
        // 결제 후 화면
        private int orderId;
        private String userName;
        private AddressDto.Response address;
        private Payment payment;
        private List<ItemDto.Response> itemList;
    }
    @Getter
    public static class toView {
        // 결제 전 화면
        private String userName;
        private List<ItemDto.Response> itemList;

        public toView(BasketDto.Response basketDto) {
            this.userName = basketDto.getUserName();
            this.itemList = basketDto.getItemList();
        }
        public toView(String userName,ItemDto.Response itemDto) {
            this.userName = userName;
            this.itemList = new ArrayList<>();
            itemList.add(itemDto);
        }
    }


    public static OrderDto.Response toOrderDtoResponse(Order order, AddressDto.Response address, Payment payment,
                                                       List<ItemDto.Response> itemList) {
        OrderDto.Response response = new Response();
        response.orderId = order.getOrderId();
        response.userName = order.getUserName();
        response.address = address;
        response.payment = payment;
        response.itemList = itemList;
        return response;
    }
}
