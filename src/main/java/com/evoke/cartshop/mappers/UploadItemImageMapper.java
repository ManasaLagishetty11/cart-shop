package com.evoke.cartshop.mappers;

import com.evoke.cartshop.dto.UploadItemImageDto;
import com.evoke.cartshop.models.UploadItemImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UploadItemImageMapper {

    UploadItemImage toEntity(UploadItemImageDto uploadItemImageDto);

    @Mapping(target = "item.uploadItemImage",ignore = true)
    @Mapping(target = "item.discount" ,ignore = true)
    UploadItemImageDto toDto(UploadItemImage uploadItemImage);
}
