package com.backendservice.mapper;

import com.backendservice.dto.ProductResponseDto;
import com.backendservice.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductResponseMapper implements Function<ProductEntity, ProductResponseDto> {

    @Override
    public ProductResponseDto apply(ProductEntity productEntity) {
        return new ProductResponseDto(
                productEntity.getProductName(),
                productEntity.getProductQuantity(),
                productEntity.getProductAmount()
        );
    }
}
