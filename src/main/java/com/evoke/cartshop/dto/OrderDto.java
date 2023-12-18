package com.evoke.cartshop.dto;

import com.evoke.cartshop.models.OrderStatus;
import com.evoke.cartshop.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderDto {
    private Long id;
    private OrderStatus orderStatus;
    private User user;
    private List<CartDto> cart;
}
