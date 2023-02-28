package com.sda.OnlineShop.repository;

import com.sda.OnlineShop.entities.SelectedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelectedProductRepository extends JpaRepository<SelectedProduct, Integer> {

    List<SelectedProduct> findSelectedProductsByShoppingCartShoppingCartId(Integer shoppingCartId);
}
