package com.jorgelazo.product.mapper;

import org.springframework.stereotype.Component;

import com.jorgelazo.product.dto.request.ProductRequest;
import com.jorgelazo.product.dto.response.ProductResponse;
import com.jorgelazo.product.entity.Product;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest productRequest) {
        
        Product product = new Product();
        product.setSku(productRequest.getSku());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setBrand(productRequest.getBrand());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setStatus(productRequest.getStatus());

        return product;
    }

    public ProductResponse toResponse(Product product) {
        
        return new ProductResponse(
            product.getId(),
            product.getSku(),
            product.getName(),
            product.getDescription(),
            product.getBrand(),
            product.getCategory(),
            product.getPrice(),
            product.getStock(),
            product.getStatus());
            
    }
}
