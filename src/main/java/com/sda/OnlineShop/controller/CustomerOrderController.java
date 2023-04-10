package com.sda.OnlineShop.controller;
import com.sda.OnlineShop.dto.CustomerOrderDto;
import com.sda.OnlineShop.dto.OrderDetailsDto;
import com.sda.OnlineShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CustomerOrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/confirmation")
    public String launchOrderPost(
            Authentication authentication,
            @ModelAttribute CustomerOrderDto customerOrderDto,
            @ModelAttribute OrderDetailsDto orderDetailsDto) {
        orderService.launchOrder(
                authentication.getName(),
                customerOrderDto,
                orderDetailsDto);
        System.out.println(customerOrderDto);

        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String launchOrderGet(Model model, Authentication authentication) {
        Optional<CustomerOrderDto> optionalCustomerOrderDto = orderService.getLaunchedCustomerOrderDto(authentication.getName());
        if(optionalCustomerOrderDto.isEmpty()){
            return "error";
        }
        CustomerOrderDto customerOrderDto = optionalCustomerOrderDto.get();
        model.addAttribute("customerOrderDto", customerOrderDto);
        return "confirmationPage";
    }
}
