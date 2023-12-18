package com.evoke.cartshop.mappers;


import com.evoke.cartshop.dto.DiscountDto;
import com.evoke.cartshop.models.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    Discount toEntity(DiscountDto discountDto);

    @Mapping(target = "item.discount",ignore = true)
    DiscountDto toDto(Discount discount);
}
