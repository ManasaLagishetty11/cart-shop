package com.evoke.cartshop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartDto {
    private Long id;

    private UserDto user;

    private ItemDto item;

    private int numberOfItems;

    private double totalValueOfItems;

}
