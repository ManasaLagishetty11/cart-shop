package com.evoke.cartshop.repositories;

import com.evoke.cartshop.models.Order;
import com.evoke.cartshop.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllOrderByUserId(Long userId);

    List<Order> findAllOrderByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}
