package com.evoke.cartshop.services;

import com.evoke.cartshop.exceptions.DuplicateAccountException;
import com.evoke.cartshop.models.Role;
import com.evoke.cartshop.models.RoleEnum;
import com.evoke.cartshop.models.User;
import com.evoke.cartshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User createUser(User user) {
        Optional<User> userExist=userRepository.findByEmail(user.getEmail());
        if (userExist.isPresent()) {
            throw new DuplicateAccountException("Email is already registered: " + user.getEmail());
        }
        String encryptedPwd=passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPwd);
       // user.setRoles(Arrays.asList((new Role(RoleEnum.CUSTOMER)),(new Role(RoleEnum.ADMIN))));
        user.setRoles(Arrays.asList(new Role(RoleEnum.ADMIN)));
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();

    }
}
