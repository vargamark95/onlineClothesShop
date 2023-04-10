package com.sda.OnlineShop.controller;

import com.sda.OnlineShop.dto.*;
import com.sda.OnlineShop.service.ProductService;
import com.sda.OnlineShop.service.ShoppingCartService;
import com.sda.OnlineShop.validators.SelectedProductDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private SelectedProductDtoValidator selectedProductDtoValidator;
    @Autowired
    private ProductService productService;

    @GetMapping("/checkout")
    public String viewCheckoutGet(Authentication authentication, Model model) {
        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCartDto(authentication.getName());
        model.addAttribute("shoppingcartDto", shoppingCartDto);

        CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        model.addAttribute("customerOrderDto", customerOrderDto);
        model.addAttribute("orderDetailsDto", orderDetailsDto);
        System.out.println(shoppingCartDto);
        return "checkout";
    }

    @PostMapping("/product/{productId}")
    public String viewProductPost(@ModelAttribute SelectedProductDto selectedProductDto,
                                  @PathVariable(value = "productId") String productId,
                                  Authentication authentication,
                                  BindingResult bindingResult,
                                  Model model){
//        System.out.println(selectedProductDto);
        selectedProductDtoValidator.validate(selectedProductDto, bindingResult,productId);
        if(bindingResult.hasErrors()){
            return getPageViewWithError(productId, model);
        }

        shoppingCartService.addToCart(selectedProductDto,productId, authentication.getName(), bindingResult);
        if(bindingResult.hasErrors()){
            return getPageViewWithError(productId, model);
        }
        return "redirect:/product/" + productId;
    }

    public String getPageViewWithError(String productId, Model model){
        Optional<ProductDto> optionalProductDto = productService.getOptionalProductDtoById(productId);
        if (optionalProductDto.isEmpty()) {
            return "error";
        }
        ProductDto productDto = optionalProductDto.get();
        model.addAttribute("productDto", productDto);
        return "viewProduct";//
    }

}
