package com.sda.OnlineShop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class OrderDetailsDto {
    private String id;
    private String contactPerson;
    private String phoneNumber;
    private String street;
    private String postcode;
    private String city;
    private String country;
    private String paymentMethod;
    private String localDate;

}
