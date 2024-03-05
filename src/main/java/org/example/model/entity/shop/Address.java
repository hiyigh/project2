package org.example.model.entity.shop;

import lombok.Builder;
import lombok.Getter;
import org.thymeleaf.dialect.IPostProcessorDialect;

@Getter
public class Address {
    private int addressId;
    private String postcode;
    private String defaultAddress;
    private String detailAddress;
    private String extraAddress;
    @Builder
    public Address(String postcode, String defaultAddress, String detailAddress, String extraAddress) {
        this.postcode = postcode;
        this.defaultAddress = defaultAddress;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
    }
}
