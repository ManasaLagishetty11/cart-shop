package com.evoke.cartshop.controllers;

import com.evoke.cartshop.dto.ItemDto;
import com.evoke.cartshop.mappers.ItemMapper;
import com.evoke.cartshop.models.Item;
import com.evoke.cartshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper mapper;

    @PostMapping("/items")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDto(itemService.save(mapper.toEntity(itemDto))));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok(mapper.toDto(itemService.findById(id)));
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> getItems() {
        return ResponseEntity.ok(mapper.toDto(itemService.getItems()));
    }


    @DeleteMapping("/items/{id}")
    public void deleteMapping(@PathVariable Long id) {
        itemService.delete(id);
    }

}
