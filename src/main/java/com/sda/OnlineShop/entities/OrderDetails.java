package com.sda.OnlineShop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class OrderDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String contactPerson;
    private String phoneNumber;
    private String street;
    private String postcode;
    private String city;
    private String country;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private LocalDate localDate;
    @OneToOne(mappedBy = "orderDetails")
    private CustomerOrder customerOrder;

}
