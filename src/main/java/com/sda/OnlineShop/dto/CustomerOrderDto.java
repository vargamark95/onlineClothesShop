package com.sda.OnlineShop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CustomerOrderDto {

    private String contactPerson;
    private String phoneNumber;
    private String address;
    private String postcode;
    private String city;
    private String county;
    private String country;
    private String paymentMethod;
    private String id;
    private List<SelectedProductDto> selectedProductDtos;
    private String localDate;
}
