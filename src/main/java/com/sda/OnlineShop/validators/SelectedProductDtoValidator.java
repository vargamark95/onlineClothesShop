package com.sda.OnlineShop.validators;

import com.sda.OnlineShop.dto.SelectedProductDto;
import com.sda.OnlineShop.entities.Product;
import com.sda.OnlineShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class SelectedProductDtoValidator {
    @Autowired
    private ProductRepository productRepository;
    public void validate(SelectedProductDto selectedProductDto, BindingResult bindingResult, String productId){
        Optional<Product> optionalProduct = productRepository.findById(Integer.valueOf(productId));
        Product product = optionalProduct.get();

        if(Integer.valueOf(selectedProductDto.getQuantity()) > product.getQuantity()){
            FieldError fieldError = new FieldError(
                    "selectedProductDto",
                    "quantity",
                    "The selected amount is too big!");
            bindingResult.addError(fieldError);
        }
    }

    public void validate(Product product, int productTotalQuantity, BindingResult bindingResult) {
        if(productTotalQuantity > product.getQuantity()){
            FieldError fieldError = new FieldError(
                    "selectedProductDto",
                    "quantity",
                    "The total selected amount is too big!");
            bindingResult.addError(fieldError);
        }
    }
}
