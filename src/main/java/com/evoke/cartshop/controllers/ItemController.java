package com.evoke.cartshop.controllers;

import com.evoke.cartshop.dto.ItemDto;
import com.evoke.cartshop.mappers.ItemMapper;
import com.evoke.cartshop.services.AwsS3Service;
import com.evoke.cartshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper mapper;

    @Autowired
    private AwsS3Service storageService;

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

    @PatchMapping("/items/{id}/{price}")
    public ResponseEntity<ItemDto> updatePrice(@PathVariable long id,@PathVariable double price) {
        return ResponseEntity.ok(mapper.toDto(itemService.updatePrice(id,price)));

    }

    @DeleteMapping("/items/{id}")
    public void deleteMapping(@PathVariable Long id) {
        itemService.delete(id);
    }


    @PostMapping("/upload")
    public String uploadImage(MultipartFile file) {
        String imageUrl = storageService.save(file);
        return imageUrl;
    }

}
