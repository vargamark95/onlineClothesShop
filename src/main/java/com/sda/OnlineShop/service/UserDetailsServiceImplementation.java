package com.sda.OnlineShop.service;

import com.sda.OnlineShop.entities.User;
import com.sda.OnlineShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAdress) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(emailAdress);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException(emailAdress);
        }
        User user = optionalUser.get();
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getUserRole().name())); //.name() transofrma Enumul in String
        return new org.springframework.security.core.userdetails.User(emailAdress, user.getPassword(), roles);
    }
}
