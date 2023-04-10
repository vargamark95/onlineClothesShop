package com.sda.OnlineShop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Join;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Integer productId;

    private String name;
    private Integer price;
    private String category;
    private Integer quantity;
    private String description;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;

    @OneToMany(mappedBy = "product")
    private List<SelectedProduct> selectedProducts;
//    @ManyToOne
//    @JoinColumn
//    private User user;
}
