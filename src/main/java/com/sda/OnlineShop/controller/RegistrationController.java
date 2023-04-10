package com.sda.OnlineShop.controller;

import com.sda.OnlineShop.dto.RegistrationDto;
import com.sda.OnlineShop.service.RegistrationService;
import com.sda.OnlineShop.validators.RegistrationDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private RegistrationDtoValidator registrationDtoValidator;

    @GetMapping("/registration")
    public String viewRegistrationGet(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("registrationDto", registrationDto);//adauga
        return "registration";
    }

    @PostMapping("/registration")
    public String viewRegistrationPost(@ModelAttribute RegistrationDto registrationDto, BindingResult bindingResult) {
        System.out.println("S-a apelat functionalitatea de viewRegistrationPost: " + registrationDto);
        registrationDtoValidator.validate(registrationDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }

        registrationService.addRegister(registrationDto);
        return "redirect: /registration";
    }
}
