package com.evoke.cartshop.services;


import com.evoke.cartshop.models.Cart;
import com.evoke.cartshop.repositories.UserRepository;
import com.evoke.cartshop.models.Order;
import com.evoke.cartshop.models.OrderStatus;
import com.evoke.cartshop.models.User;
import com.evoke.cartshop.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.evoke.cartshop.repositories.OrderRepository;


import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(Long userId) {
       List<Cart> cartList= cartRepository.findCartByUserId(userId);
       //remove cart items once they are saved in orders
       Optional<User> user= userRepository.findById(userId);

       Order order=new Order();
       order.setUser(user.get());
       order.setOrderStatus(OrderStatus.RECEIVED);
       order.setCart(cartList);

       return orderRepository.save(order);

    }
}
