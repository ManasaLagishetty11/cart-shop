package com.evoke.cartshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartItemsAndTotalCartValueDto {

    double totalCartValue;

    List<CartDto> cartDtoList;
}
