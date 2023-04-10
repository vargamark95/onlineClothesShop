package com.sda.OnlineShop.service;

import com.sda.OnlineShop.dto.ShoppingCartDto;
import com.sda.OnlineShop.entities.Product;
import com.sda.OnlineShop.entities.SelectedProduct;
import com.sda.OnlineShop.dto.SelectedProductDto;
import com.sda.OnlineShop.entities.ShoppingCart;
import com.sda.OnlineShop.mapper.ShoppingCartMapper;
import com.sda.OnlineShop.repository.ProductRepository;
import com.sda.OnlineShop.repository.SelectedProductRepository;
import com.sda.OnlineShop.repository.ShoppingCartRepository;
import com.sda.OnlineShop.validators.SelectedProductDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private SelectedProductRepository selectedProductRepository;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private SelectedProductDtoValidator selectedProductDtoValidator;


    public void addToCart(SelectedProductDto selectedProductDto, String productId, String authenticatedUserEmail, BindingResult bindingResult) {

        Optional<Product> optionalProduct = productRepository.findById(Integer.valueOf(productId));
        Product product = optionalProduct.get();
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserEmail(authenticatedUserEmail);

        List<SelectedProduct> selectedProducts = shoppingCart.getSelectedProducts();
        for(SelectedProduct selectedProduct : selectedProducts){
            if(selectedProduct.getProduct().getProductId() == product.getProductId()){
                int productTotalQuantity = selectedProduct.getQuantity() + Integer.parseInt(selectedProductDto.getQuantity());
                selectedProductDtoValidator.validate(product, productTotalQuantity, bindingResult);
                if(bindingResult.hasErrors()){
                    return;
                }
                selectedProduct.setQuantity(selectedProduct.getQuantity() + Integer.parseInt(selectedProductDto.getQuantity()));
                selectedProductRepository.save(selectedProduct);
                return;
            }
        }
        SelectedProduct selectedProduct = buildProduct(selectedProductDto, product, shoppingCart);
        selectedProductRepository.save(selectedProduct);
    }

    private SelectedProduct buildProduct(SelectedProductDto selectedProductDto, Product product, ShoppingCart shoppingCart) {
        SelectedProduct selectedProduct = new SelectedProduct();
        selectedProduct.setProduct(product);
        selectedProduct.setQuantity(Integer.valueOf(selectedProductDto.getQuantity()));
        selectedProduct.setShoppingCart(shoppingCart);
        return selectedProduct;
    }

    public ShoppingCartDto getShoppingCartDto(String authenticatedUserEmail) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserEmail(authenticatedUserEmail);
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.map(shoppingCart);

        return shoppingCartDto;
    }

}
