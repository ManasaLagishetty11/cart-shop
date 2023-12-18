package com.evoke.cartshop.controllers;


import com.evoke.cartshop.dto.DiscountDto;
import com.evoke.cartshop.mappers.DiscountMapper;
import com.evoke.cartshop.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private DiscountMapper discountMapper;

    @PostMapping("/discounts/{discountPercentage}/items/{itemId}")
    public ResponseEntity<DiscountDto> applyDiscountOnItem(@PathVariable Long itemId, @PathVariable Double discountPercentage) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(discountMapper.toDto(discountService.applyDiscountOnItem(itemId, discountPercentage)));
    }
}


