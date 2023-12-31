package com.evoke.cartshop.mappers;

import com.evoke.cartshop.dto.AddressDto;
import com.evoke.cartshop.models.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressDto addressDto);

    @Mapping(target = "user.address", ignore = true)
    AddressDto toDto(Address address);

    List<AddressDto> toDto(List<Address> addressList);
}
