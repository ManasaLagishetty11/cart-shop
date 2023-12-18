package com.evoke.cartshop.controllers;


import com.evoke.cartshop.dto.CartDto;
import com.evoke.cartshop.dto.CartItemsAndTotalCartValueDto;
import com.evoke.cartshop.mappers.CartMapper;
import com.evoke.cartshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;


    @PostMapping("/carts/{userId}/items/{itemId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long userId, @PathVariable Long itemId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartMapper.toDto(cartService.addItemToCart(userId, itemId)));
    }

    @PatchMapping("/carts/{cartId}/items/{numberOfItems}")
    public ResponseEntity<CartDto> updateNumberOfItemsInCart(@PathVariable Long cartId, @PathVariable int numberOfItems) {
        return ResponseEntity.ok(cartMapper.toDto(cartService.updateNumberOfItemsInCart(cartId, numberOfItems)));
    }

    @GetMapping("/carts/{userId}")
    public ResponseEntity<CartItemsAndTotalCartValueDto> getCartItemsOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));

    }
}