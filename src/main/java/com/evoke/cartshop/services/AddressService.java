package com.evoke.cartshop.services;

import com.evoke.cartshop.exceptions.ResourceNotFoundException;
import com.evoke.cartshop.models.Address;
import com.evoke.cartshop.models.User;
import com.evoke.cartshop.repositories.AddressRepository;
import com.evoke.cartshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public Address saveAddress(Address address, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User not found " + userId);
        }
        address.setUser(user.get());
        return addressRepository.save(address);
    }

    public List<Address> getAllSavedAddressOfUser(Long userId) {
        return addressRepository.findAllByUserId(userId);
    }
}





