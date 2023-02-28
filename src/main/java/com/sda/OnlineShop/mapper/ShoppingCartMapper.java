package com.sda.OnlineShop.mapper;

import com.sda.OnlineShop.dto.SelectedProductDto;
import com.sda.OnlineShop.dto.ShoppingCartDto;
import com.sda.OnlineShop.entities.SelectedProduct;
import com.sda.OnlineShop.entities.ShoppingCart;
import com.sda.OnlineShop.repository.SelectedProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCartMapper {
    @Autowired
    private SelectedProductRepository selectedProductRepository;
    @Autowired
    private SelectedProductMapper selectedProductMapper;

    final int shipping = 10;

    public ShoppingCartDto map(ShoppingCart shoppingCart){
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();

        List<SelectedProduct> selectedProducts = selectedProductRepository.findSelectedProductsByShoppingCartShoppingCartId(shoppingCart.getShoppingCartId());
        List<SelectedProductDto> selectedProductDtos = new ArrayList<>();
        for(SelectedProduct selectedProduct : selectedProducts){
            SelectedProductDto selectedProductDto = selectedProductMapper.map(selectedProduct);
            selectedProductDtos.add(selectedProductDto);
        }

        shoppingCartDto.setSelectedProductDtos(selectedProductDtos);
        shoppingCartDto.setSubTotal(String.valueOf(getSubTotal(selectedProducts)));
        shoppingCartDto.setShipping(String.valueOf(shipping));
        shoppingCartDto.setTotal(String.valueOf(getTotal(shoppingCart)));

        return shoppingCartDto;
    }

    private Integer getSubTotal(List<SelectedProduct> selectedProducts){
        Integer subTotal = 0;
        for(SelectedProduct selectedProduct : selectedProducts){
            Integer productPriceTimesQuantity = selectedProduct.getPrice() * selectedProduct.getQuantity();
            subTotal += productPriceTimesQuantity;
        }
        return subTotal;
    }

    private Integer getTotal(ShoppingCart shoppingCart){
        List<SelectedProduct> selectedProducts = selectedProductRepository.findSelectedProductsByShoppingCartShoppingCartId(shoppingCart.getShoppingCartId());
        return getSubTotal(selectedProducts) + shipping;
    }
}
