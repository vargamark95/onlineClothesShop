package com.sda.OnlineShop.validators;
import com.sda.OnlineShop.dto.RegistrationDto;
import com.sda.OnlineShop.entities.User;
import com.sda.OnlineShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationDtoValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(RegistrationDto registrationDto, BindingResult bindingResult) {

        validateEmail(registrationDto, bindingResult);
        validatePhoneNumber(registrationDto, bindingResult);
        validatePassword(registrationDto, bindingResult);


    }

    private void validatePassword(RegistrationDto registrationDto, BindingResult bindingResult) {
        if(!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())){
            FieldError fieldError = new FieldError("registrationDto", "confirmPassword", "The passwords don't match!");
            bindingResult.addError(fieldError);
        }
    }

    private void validateEmail(RegistrationDto registrationDto, BindingResult bindingResult) {
        Optional<User> optionalUser = userRepository.findByEmail(registrationDto.getEmailAdress());
        if (!optionalUser.isEmpty()) {
            FieldError fieldError = new FieldError("registrationDto", "emailAdress", "The email is already in use!");
            bindingResult.addError(fieldError);
        }

        Pattern pattern = Pattern.compile( "^(.+)@(.+).[a-z]$");
        Matcher matcher = pattern.matcher(registrationDto.getEmailAdress());
        if(!matcher.matches()){
            FieldError fieldError = new FieldError("registrationDto", "emailAdress", "The email provided is not valid!");
            bindingResult.addError(fieldError);
        }
    }

    private void validatePhoneNumber(RegistrationDto registrationDto, BindingResult bindingResult) {
        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?(\\d{10}$)");
        Matcher matcher = pattern.matcher(registrationDto.getPhoneNumber());
        if(!matcher.matches()){
            FieldError fieldError = new FieldError("registrationDto", "phoneNumber", "The phone number is incorrect");
            bindingResult.addError(fieldError);
        }
    }
}
