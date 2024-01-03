package com.evoke.cartshop.services;

import com.evoke.cartshop.exceptions.DuplicateAccountException;
import com.evoke.cartshop.exceptions.ResourceNotFoundException;
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

    @Autowired
    private  EmailService emailService;


    public User createUser(User user) {
        Optional<User> userExist = userRepository.findByEmail(user.getEmail());
        if (userExist.isPresent()) {
            throw new DuplicateAccountException("Email is already registered: " + user.getEmail());
        }
        String encryptedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPwd);
        user.setRoles(Arrays.asList(new Role(RoleEnum.ADMIN)));
        emailService.sendMailtoUserForSuccessfulRegistration(user);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent())
            throw new ResourceNotFoundException("User not Found" + email);
        else
            return user.get();
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new ResourceNotFoundException("User not Found" +id);
        else
            return user.get();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
