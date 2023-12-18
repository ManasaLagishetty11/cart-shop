package com.evoke.cartshop.services;

import com.evoke.cartshop.dto.CartDto;
import com.evoke.cartshop.dto.CartItemsAndTotalCartValueDto;
import com.evoke.cartshop.exceptions.ResourceNotFoundException;
import com.evoke.cartshop.mappers.CartMapper;
import com.evoke.cartshop.mappers.ItemMapper;
import com.evoke.cartshop.mappers.UserMapper;
import com.evoke.cartshop.models.Cart;
import com.evoke.cartshop.models.Discount;
import com.evoke.cartshop.models.Item;
import com.evoke.cartshop.models.User;
import com.evoke.cartshop.repositories.CartRepository;
import com.evoke.cartshop.repositories.DiscountRepository;
import com.evoke.cartshop.repositories.ItemRepository;
import com.evoke.cartshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private CartMapper cartMapper;

    public Cart addItemToCart(Long userId, Long itemId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.get().getNumberOfItems() > 0) {
            CartDto cart = new CartDto();
            cart.setItem(itemMapper.toDto(item.get()));
            cart.setUser(userMapper.toDto(user.get()));
            cart.setNumberOfItems(1);
            cart.setTotalValueOfItems(setValueOfItems(item.get()));
            updateNumberOfItems(item.get(), 1);
            return cartRepository.save(cartMapper.toEntity(cart));
        } else
            throw new ResourceNotFoundException("item not available");//custom exception for item not available
    }


    public CartItemsAndTotalCartValueDto getCartItems(Long userId) {
        List<Cart> cartList = cartRepository.findCartByUserId(userId);
        CartItemsAndTotalCartValueDto cartItemsAndTotalValue = new CartItemsAndTotalCartValueDto();
        cartItemsAndTotalValue.setCartDtoList(cartMapper.toDto(cartList));
        cartItemsAndTotalValue.setTotalCartValue(calculateTotalCartValue(cartList));
        return cartItemsAndTotalValue;
    }

    private double calculateTotalCartValue(List<Cart> cartList) {
        double totalCartValue = 0;
        for (Cart cart : cartList) {
            totalCartValue = totalCartValue + cart.getTotalValueOfItems();
        }
        return totalCartValue;
    }

    public Cart updateNumberOfItemsInCart(Long cartId, int numberOfItems) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.setNumberOfItems(numberOfItems);
        cart.setTotalValueOfItems(setValueOfItems(cart.getItem()) * numberOfItems);
        updateNumberOfItems(cart.getItem(), numberOfItems);
        return cartRepository.save(cart);
    }

    private double setValueOfItems(Item item) {
        boolean isDiscountAvailable = item.getDiscount().isDiscountAvailable();
        double itemPrice = item.getPrice();
        if (isDiscountAvailable)
            return calculateDiscountPrice(discountRepository.findDiscountByItemId(item.getId()), itemPrice);
        else
            return item.getPrice();
    }

    private double calculateDiscountPrice(Discount discount, double itemPrice) {
        double discountPrice = (discount.getDiscountPercentage() / 100) * itemPrice;
        double offerPrice = itemPrice - discountPrice;
        return offerPrice;
    }

    private void updateNumberOfItems(Item item, int numberOfItems) {
        item.setNumberOfItems(item.getNumberOfItems() - numberOfItems);
        itemRepository.save(item);
    }
}
