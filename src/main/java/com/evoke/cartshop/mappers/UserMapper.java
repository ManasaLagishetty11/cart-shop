package com.evoke.cartshop.mappers;

import com.evoke.cartshop.dto.UserDto;
import com.evoke.cartshop.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    @Mapping(target = "address.user",ignore = true)
    UserDto toDto(User user);


}
