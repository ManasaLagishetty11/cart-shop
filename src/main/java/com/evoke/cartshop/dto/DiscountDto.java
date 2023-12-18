package com.evoke.cartshop.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiscountDto {
    private Long id;
    private ItemDto item;
    private Double offerPrice;
    private Double discountPercentage;
    private boolean isDiscountAvailable;
}




