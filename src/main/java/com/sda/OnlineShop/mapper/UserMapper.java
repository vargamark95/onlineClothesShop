package com.sda.OnlineShop.mapper;

import com.sda.OnlineShop.dto.RegistrationDto;
import com.sda.OnlineShop.entities.ShoppingCart;
import com.sda.OnlineShop.entities.User;
import com.sda.OnlineShop.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public User map(RegistrationDto registrationDto){
        User user = new User();
        user.setFullName(registrationDto.getFullName());
        user.setEmail(registrationDto.getEmailAdress());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        user.setAdress(registrationDto.getAdress());
        user.setPhoneNumber(registrationDto.getPhoneNumber());
        user.setUserRole(UserRole.valueOf(registrationDto.getUserRole()));

        ShoppingCart shoppingCart = new ShoppingCart();
        user.setShoppingCart(shoppingCart);
        shoppingCart.setUser(user);
        return user;

    }
}
