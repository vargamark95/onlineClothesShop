package com.sda.OnlineShop.entities;

import com.sda.OnlineShop.service.ProductService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.sda.OnlineShop.entities.Product;

@Entity
@Getter
@Setter
public class SelectedProduct {
    @Id
    @GeneratedValue
    private Integer selectedProductId;
    @ManyToOne
    @JoinColumn
    private Product product;
    private Integer quantity;

    @ManyToOne
    @JoinColumn
    private ShoppingCart shoppingCart;

}
