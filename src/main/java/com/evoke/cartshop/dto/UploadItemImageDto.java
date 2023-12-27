package com.evoke.cartshop.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UploadItemImageDto {

    private Long id;
    private String imageUrl;
    private ItemDto item;
}
