package com.sda.OnlineShop.service;

import com.sda.OnlineShop.dto.ShoppingCartDto;
import com.sda.OnlineShop.entities.Product;
import com.sda.OnlineShop.entities.SelectedProduct;
import com.sda.OnlineShop.dto.SelectedProductDto;
import com.sda.OnlineShop.entities.ShoppingCart;
import com.sda.OnlineShop.entities.User;
import com.sda.OnlineShop.mapper.ShoppingCartMapper;
import com.sda.OnlineShop.repository.ProductRepository;
import com.sda.OnlineShop.repository.SelectedProductRepository;
import com.sda.OnlineShop.repository.ShoppingCartRepository;
import com.sda.OnlineShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;



    public void addToCart(SelectedProductDto selectedProductDto,
                          String productId,
                          String authenticatedUserEmail){
        Optional<Product> optionalProduct = productRepository.findById(Integer.valueOf(productId));
        Product product = optionalProduct.get();
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserEmail(authenticatedUserEmail);


        SelectedProduct selectedProduct = buildProduct(selectedProductDto, product, shoppingCart);

        selectedProductRepository.save(selectedProduct);
        //selectedProductRe
    }

    private SelectedProduct buildProduct(SelectedProductDto selectedProductDto, Product product, ShoppingCart shoppingCart) {
        SelectedProduct selectedProduct = new SelectedProduct();
        selectedProduct.setProduct(product);
        selectedProduct.setQuantity(Integer.valueOf(selectedProductDto.getQuantity()));
        selectedProduct.setShoppingCart(shoppingCart);
        selectedProduct.setPrice(product.getPrice());
        selectedProduct.setName(product.getName());
        return selectedProduct;
    }



    public ShoppingCartDto getShoppingCartDto(String authenticatedUserEmail){
        Optional<User> optionalUser = userRepository.findByEmail(authenticatedUserEmail);
        User user = optionalUser.get();
        ShoppingCart shoppingCart = user.getShoppingCart();
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.map(shoppingCart);


         return shoppingCartDto;
    }

}
