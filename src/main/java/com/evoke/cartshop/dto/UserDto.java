package com.evoke.cartshop.dto;

import com.evoke.cartshop.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private AddressDto address;
    private List<Role> roles;
}
