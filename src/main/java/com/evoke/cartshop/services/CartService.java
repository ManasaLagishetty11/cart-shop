package com.evoke.cartshop.services;

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
        if (user.isPresent() && item.isPresent()) {
            if (item.get().getNumberOfItems() > 0) {
                Cart cart = new Cart();
                cart.setItem(item.get());
                cart.setUser(user.get());
                cart.setNumberOfItems(1);
                cart.setVisible(true);
                cart.setTotalValueOfItems(setValueOfItems(item.get()));
                updateNumberOfItems(item.get(), 1);
                return cartRepository.save(cart);
            } else
                throw new ResourceNotFoundException("item not available");
        } else {
            throw new ResourceNotFoundException("user or item not present");
        }
    }


    public CartItemsAndTotalCartValueDto getCartItems(Long userId) {
        List<Cart> cartList = cartRepository.findCartByUserIdAndIsVisibleIsTrue(userId);
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
        double itemPrice = item.getPrice();
        if (item.getDiscount() != null)
            return calculateDiscountPrice(discountRepository.findDiscountByItemId(item.getId()), itemPrice);
        else
            return item.getPrice();
    }

    private double calculateDiscountPrice(Discount discount, double itemPrice) {
        double discountPrice = (discount.getDiscountPercentage() / 100) * itemPrice;
        return itemPrice - discountPrice;
    }

    private void updateNumberOfItems(Item item, int numberOfItems) {
        if (numberOfItems > item.getNumberOfItems()) {
            throw new ResourceNotFoundException("Items not available");
        }
        item.setNumberOfItems(item.getNumberOfItems() - numberOfItems);
        itemRepository.save(item);
    }

    public void removeCartItemsAfterOrderConfirmation(List<Long> idList) {
        for (Long id : idList) {
            Optional<Cart> cart = cartRepository.findById(id);
            cart.get().setVisible(false);
            cartRepository.save(cart.get());
        }
    }
}
