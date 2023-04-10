package com.sda.OnlineShop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String fullName;
    private String email;
    private String password;
    private String adress;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;
    @OneToMany(mappedBy = "user")
    private List<CustomerOrder> customerOrders;
//    @OneToMany(mappedBy = "soldBy")
//    private List<Product> productsSold;
}
