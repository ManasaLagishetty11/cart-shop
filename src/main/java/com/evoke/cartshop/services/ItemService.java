package com.evoke.cartshop.services;

import com.evoke.cartshop.exceptions.ResourceNotFoundException;
import com.evoke.cartshop.models.Item;
import com.evoke.cartshop.models.UploadItemImage;
import com.evoke.cartshop.repositories.ItemRepository;
import com.evoke.cartshop.repositories.UploadItemImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AwsS3Service storageService;

    @Autowired
    private UploadItemImageRepository uploadItemImageRepository;

    public Item save(Item item) {

        return itemRepository.save(item);
    }

    public List<Item> getItems() {

        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        Optional<Item> item=itemRepository.findById(id);
        if(!item.isPresent()){
            throw new ResourceNotFoundException("item not available: "+id);
        }
        return item.get();
    }

    public void delete(Long id) {

        itemRepository.deleteById(id);
    }

    public Item updatePrice(Long id, Double price) {
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException("Item not Found" +id);
        }
        item.get().setPrice(price);
        return itemRepository.save(item.get());
    }

    public UploadItemImage uploadImage(Long itemId, MultipartFile file) {
        String url = storageService.save(file);
        UploadItemImage uploadItemImage = new UploadItemImage();
        Optional<Item> item=itemRepository.findById(itemId);
        if(!item.isPresent()){
            throw new ResourceNotFoundException("Item not available "+itemId);
        }
        uploadItemImage.setItem(item.get());
        uploadItemImage.setImageUrl(url);
        return uploadItemImageRepository.save(uploadItemImage);
    }
}
