package com.sda.OnlineShop.repository;

import com.sda.OnlineShop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {


   // List<Product> findByUserId(Integer id);

}
