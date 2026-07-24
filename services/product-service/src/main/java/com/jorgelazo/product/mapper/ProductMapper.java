package com.jorgelazo.product.mapper;

import org.springframework.stereotype.Component;

import com.jorgelazo.product.dto.request.ProductRequest;
import com.jorgelazo.product.dto.response.ProductResponse;
import com.jorgelazo.product.entity.Product;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest productRequest) {
        
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        return product;
    }

    public ProductResponse toResponse(Product product) {
        
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStock()
        );
    }
}
