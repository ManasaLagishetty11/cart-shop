package com.evoke.cartshop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
    private Long id;
    private String area;
    private String city;
    private String country;
    private String zipcode;
    private UserDto user;
}
