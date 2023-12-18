package com.evoke.cartshop.controllers;


import com.evoke.cartshop.dto.OrderDto;
import com.evoke.cartshop.mappers.OrderMapper;
import com.evoke.cartshop.models.OrderStatus;
import com.evoke.cartshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/orders/{userId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.toDto(orderService.createOrder(userId)));
        //address id
    }

    @PatchMapping("/orders/{id}/confirm")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.updateOrder(id)));
//admin
    }

    @PatchMapping("/orders/{id}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.cancelOrder(id)));
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<OrderDto>> getOrders(@PathVariable Long userId, @RequestParam(name = "orderStatus", required = false) OrderStatus orderStatus) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.findOrders(userId, orderStatus)));
    }
}
