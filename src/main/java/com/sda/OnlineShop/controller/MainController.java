package com.sda.OnlineShop.controller;

import com.sda.OnlineShop.dto.ProductDto;
import com.sda.OnlineShop.dto.RegistrationDto;
import com.sda.OnlineShop.dto.ShoppingCartDto;
import com.sda.OnlineShop.dto.SelectedProductDto;
import com.sda.OnlineShop.service.ProductService;
import com.sda.OnlineShop.service.RegistrationService;
import com.sda.OnlineShop.service.ShoppingCartService;
import com.sda.OnlineShop.validators.RegistrationDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private RegistrationDtoValidator registrationDtoValidator;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/addProduct")
    public String addProductGet(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        //teoretic aici executam business logic
        //dupa care intoarcem un nume de pagina
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductPost(@ModelAttribute ProductDto productDto,
                                 @RequestParam("productImage") MultipartFile multipartFile) {
        productService.addProduct(productDto, multipartFile);
        System.out.println(productDto);
        return "redirect:/addProduct";
    }

    @GetMapping("/home")
    public String homeGet(Model model) {
        List<ProductDto> productDtos = productService.getAllProductDtos();
        model.addAttribute("productDtos", productDtos);
        return "home";
    }

    @GetMapping("product/{productId}")
    public String productViewGet(@PathVariable(value = "productId") String productId, Model model) {
        Optional<ProductDto> optionalProductDto = productService.getOptionalProductDtoById(productId);
        if (optionalProductDto.isEmpty()) {
            return "error";
        }
        ProductDto productDto = optionalProductDto.get();
        model.addAttribute("productDto", productDto);

        SelectedProductDto selectedProductDto = new SelectedProductDto();
        model.addAttribute("selectedProductDto", selectedProductDto);
        System.out.println("Am dat click pe produsul cu id-ul " + productId);
        return "viewProduct";
    }

    @PostMapping("/product/{productId}")
    public String viewProductPost(@ModelAttribute SelectedProductDto selectedProductDto,
                                  @PathVariable(value = "productId") String productId,
                                  Authentication authentication){
        System.out.println(selectedProductDto);
        System.out.println(authentication.getName());

        shoppingCartService.addToCart(selectedProductDto,productId, authentication.getName());
        return "redirect:/product/" + productId;
    }

    @GetMapping("/registration")
    public String viewRegistrationGet(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("registrationDto", registrationDto);//adauga
        return "registration";
    }

    @PostMapping("/registration")
    public String viewRegistrationPost(@ModelAttribute RegistrationDto registrationDto, BindingResult bindingResult) {
        System.out.println("S-a apelat functionalitatea de viewRegistrationPost: " + registrationDto);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        registrationDtoValidator.validate(registrationDto, bindingResult);
        registrationService.addRegister(registrationDto);
        return "registration";
    }

    @GetMapping("/login")
    public String viewLoginGet() {
        return "login";
    }

    @GetMapping("/checkout")
    public String viewCheckoutGet(Authentication authentication, Model model){
        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCartDto(authentication.getName());

        System.out.println(shoppingCartDto);
        model.addAttribute("shoppingcartDto", shoppingCartDto);
        return "checkout";
    }


}


