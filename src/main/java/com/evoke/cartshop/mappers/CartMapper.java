package com.evoke.cartshop.mappers;

import com.evoke.cartshop.dto.CartDto;
import com.evoke.cartshop.models.Cart;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring", uses = {ItemMapper.class, UserMapper.class})
public interface CartMapper {

    Cart toEntity(CartDto cartDto);

    CartDto toDto(Cart cart);

    List<CartDto> toDto(List<Cart> cartList);
}
