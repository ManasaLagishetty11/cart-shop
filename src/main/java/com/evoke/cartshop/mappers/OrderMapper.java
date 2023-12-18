package com.evoke.cartshop.mappers;


import com.evoke.cartshop.dto.OrderDto;
import com.evoke.cartshop.models.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = CartMapper.class)
public interface OrderMapper {

    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

    List<OrderDto> toDto(List<Order> orderList);

}
