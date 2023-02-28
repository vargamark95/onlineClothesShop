package com.sda.OnlineShop.mapper;

import com.sda.OnlineShop.dto.SelectedProductDto;
import com.sda.OnlineShop.entities.Product;
import com.sda.OnlineShop.entities.SelectedProduct;
import com.sda.OnlineShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectedProductMapper {
    @Autowired
    private ProductRepository productRepository;

    public SelectedProductDto map(SelectedProduct selectedProduct){
        SelectedProductDto selectedProductDto = new SelectedProductDto();

        selectedProductDto.setName(selectedProduct.getName());
        selectedProductDto.setQuantity(String.valueOf(selectedProduct.getQuantity()));
        selectedProductDto.setPrice(String.valueOf(selectedProduct.getPrice()));
        selectedProductDto.setPriceTimesQuantity(getPriceTimesQuantity(selectedProduct));

        return selectedProductDto;
    }


    private String getPriceTimesQuantity(SelectedProduct selectedProduct){
        Integer price = selectedProduct.getPrice();
        Integer quantity = selectedProduct.getQuantity();
        return String.valueOf(price*quantity);
    }

}
