package com.evoke.cartshop.mappers;

import com.evoke.cartshop.dto.ItemDto;
import com.evoke.cartshop.models.Item;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemDto itemDto);

    @Mapping(target="discount.item",ignore = true)
    @Mapping(target="uploadItemImage.item",ignore = true)
    ItemDto toDto(Item item);

    List<ItemDto> toDto(List<Item> itemList);

}
