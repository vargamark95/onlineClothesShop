package com.sda.OnlineShop.dto;

import com.sda.OnlineShop.entities.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
public class RegistrationDto {

    private String fullName;
    private String emailAdress;
    private String password;
    private String confirmPassword;
    private String adress;
    private String phoneNumber;
    private String userRole;


}
