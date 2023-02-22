package com.sda.OnlineShop.validators;

import com.sda.OnlineShop.dto.RegistrationDto;
import com.sda.OnlineShop.entities.User;
import com.sda.OnlineShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class RegistrationDtoValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(RegistrationDto registrationDto, BindingResult bindingResult) {
        Optional<User> optionalUser = userRepository.findByEmail(registrationDto.getEmailAdress());
        if (!optionalUser.isEmpty()) {
            FieldError fieldError = new FieldError("registrationDto", "emailAdress", "The email is already in use");
            bindingResult.addError(fieldError);
        } else {

        }


    }
}
