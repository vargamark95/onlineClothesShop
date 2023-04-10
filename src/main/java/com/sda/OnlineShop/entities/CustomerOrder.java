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
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToOne
    @JoinColumn
    @ToString.Exclude
    private OrderDetails orderDetails;


}
