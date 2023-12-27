package com.evoke.cartshop.dto;

import com.evoke.cartshop.models.ItemCategory;
import com.evoke.cartshop.models.ItemStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private ItemCategory category;
    private Long numberOfItems;
    private ItemStatus status;
    private boolean availability;
    private DiscountDto discount;
    private UploadItemImageDto uploadItemImage;
}
