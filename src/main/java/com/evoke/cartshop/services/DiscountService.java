package com.evoke.cartshop.services;

import com.evoke.cartshop.exceptions.ResourceNotFoundException;
import com.evoke.cartshop.models.Discount;
import com.evoke.cartshop.models.Item;
import com.evoke.cartshop.repositories.DiscountRepository;
import com.evoke.cartshop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Discount applyDiscountOnItem(Long id, Double discountPercentage) {
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException("Item is not present to apply the discount");
        }
        Discount discount = new Discount();
        Double price = item.get().getPrice();
        Double percentageOfPrice = (discountPercentage / 100) * price;
        Double offerPrice = price - percentageOfPrice;
        discount.setDiscountPercentage(discountPercentage);
        discount.setOfferPrice(offerPrice);
        discount.setItem(item.get());
        discount.setDiscountAvailable(true);
        return discountRepository.save(discount);

    }

    public Discount updateDiscount(Long id, Double discountPercentage) {
        Optional<Discount> discount = discountRepository.findById(id);
        if (!discount.isPresent()) {
            throw new ResourceNotFoundException("discount on Item not available" + id);
        }
        Double price = discount.get().getItem().getPrice();
        Double percentageOfPrice = (discountPercentage / 100) * price;
        Double offerPrice = price - percentageOfPrice;
        discount.get().setOfferPrice(offerPrice);
        discount.get().setDiscountPercentage(discountPercentage);
        return discountRepository.save(discount.get());
    }

    public void deleteDiscountOnItem(Long id) {
        discountRepository.deleteById(id);
    }
}
