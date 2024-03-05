package org.example.model.dto.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.shop.Address;

public class AddressDto {
    @Getter
    @Setter
    public static class Request {
        private String postcode;
        private String defaultAddress;
        private String detailAddress;
        private String extraAddress;
    }
    @Getter
    public static class Response {
        private int addressId;
        private String postcode;
        private String defaultAddress;
        private String detailAddress;
        private String extraAddress;
    }
    public static AddressDto.Response toAddressDtoResponse(Address address) {
        AddressDto.Response response = new Response();
        response.addressId = address.getAddressId();
        response.postcode = address.getPostcode();
        response.defaultAddress = address.getDefaultAddress();
        response.detailAddress = address.getDefaultAddress();
        response.extraAddress = address.getExtraAddress();
        return response;
    }
}
