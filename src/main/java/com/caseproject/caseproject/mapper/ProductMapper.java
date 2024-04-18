package com.caseproject.caseproject.mapper;

import org.springframework.stereotype.Component;

import com.caseproject.caseproject.entities.Product;
import com.caseproject.caseproject.response.ProductResponse;

@Component
public class ProductMapper {
    public ProductResponse productToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
