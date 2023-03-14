package com.sda.OnlineShop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class CustomerOrder {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn
    private User user;
    @OneToMany(mappedBy = "customerOrder")
    private List<SelectedProduct> selectedProducts;

    private String contactPerson;
    private String phoneNumber;
    private String address;
    private String postcode;
    private String city;
    private String county;
    private String country;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private LocalDate localDate;
}
