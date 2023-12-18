package com.evoke.cartshop.services;


import com.evoke.cartshop.exceptions.ResourceNotFoundException;
import com.evoke.cartshop.models.Cart;
import com.evoke.cartshop.repositories.UserRepository;
import com.evoke.cartshop.models.Order;
import com.evoke.cartshop.models.OrderStatus;
import com.evoke.cartshop.models.User;
import com.evoke.cartshop.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.evoke.cartshop.repositories.OrderRepository;


import java.util.ArrayList;
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

    @Autowired
    private CartService cartService;

    public Order createOrder(Long userId) {
        List<Cart> cartList = cartRepository.findCartByUserIdAndIsVisibleIsTrue(userId);
        Optional<User> user = userRepository.findById(userId);
        Order order = new Order();
        order.setUser(user.get());
        order.setOrderStatus(OrderStatus.RECEIVED);
        order.setCart(cartList);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new ResourceNotFoundException("order not available");
        }
        order.get().setOrderStatus(OrderStatus.CONFIRMED);
        getCartOfUser(id);
        return orderRepository.save(order.get());
    }

    private void getCartOfUser(Long id) {
        List<Cart> cartList = orderRepository.findById(id).get().getCart();
        List<Long> idList = new ArrayList<>();
        for (Cart cart : cartList) {
            idList.add(cart.getId());
        }
        cartService.removeCartItemsAfterOrderConfirmation(idList);
    }

    public Order cancelOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        order.get().setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order.get());
    }

    public List<Order> findOrders(Long userId, OrderStatus orderStatus) {
        if (orderStatus == null)
            return orderRepository.findAllOrderByUserId(userId);
        else
            return orderRepository.findAllOrderByUserIdAndOrderStatus(userId,orderStatus);
    }
}
