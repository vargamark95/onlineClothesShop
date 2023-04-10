package com.sda.OnlineShop.controller;

import com.sda.OnlineShop.dto.ProductDto;
import com.sda.OnlineShop.dto.SelectedProductDto;
import com.sda.OnlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;



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






}
