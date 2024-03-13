package org.example.model.dto.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.shop.Address;

public class AddressDto {
    @Getter
    @Setter
    public static class Request {
        private String postcode;
        private String roadAddress;
        private String jibunAddress;
        private String detailAddress;
    }
    @Getter
    public static class Response {
        private int addressId;
        private String postcode;
        private String roadAddress;
        private String jibunAddress;
        private String detailAddress;
    }
    public static AddressDto.Response toAddressDtoResponse(Address address) {
        AddressDto.Response response = new Response();
        response.addressId = address.getAddressId();
        response.postcode = address.getPostcode();
        response.roadAddress = address.getDefaultAddress();
        response.jibunAddress = address.getDefaultAddress();
        response.detailAddress = address.getExtraAddress();
        return response;
    }
}
