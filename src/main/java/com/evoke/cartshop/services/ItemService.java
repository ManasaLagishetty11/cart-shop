package com.evoke.cartshop.services;

import com.evoke.cartshop.exceptions.ResourceNotFoundException;
import com.evoke.cartshop.models.Item;
import com.evoke.cartshop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item save(Item item) {

        return itemRepository.save(item);
    }

    public List<Item> getItems() {

        return itemRepository.findAll();
    }

    public Item findById(Long id) {

        return itemRepository.findById(id).get();
    }

    public void delete(Long id) {

        itemRepository.deleteById(id);
    }

    public Item updatePrice(long id, double price) {
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException("Item not Found" + item.get().getId());
        }
        item.get().setPrice(price);
        return itemRepository.save(item.get());
    }
}
