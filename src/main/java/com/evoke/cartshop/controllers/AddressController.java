package com.evoke.cartshop.controllers;


import com.evoke.cartshop.dto.AddressDto;
import com.evoke.cartshop.mappers.AddressMapper;
import com.evoke.cartshop.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressMapper addressMapper;

    @PostMapping("/address/{userId}")
    public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressDto, @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addressMapper.toDto(addressService.saveAddress(addressMapper.toEntity(addressDto), userId)));
    }

    @GetMapping("/address/{userId}")
    public ResponseEntity<List<AddressDto>> getAllAddressOfUser(@PathVariable Long userId){
            return ResponseEntity.ok(addressMapper.toDto(addressService.getAllSavedAddressOfUser(userId)));
    }
}
