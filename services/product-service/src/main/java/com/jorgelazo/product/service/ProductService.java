package com.jorgelazo.product.service;

import com.jorgelazo.product.dto.request.ProductRequest;
import com.jorgelazo.product.dto.response.ProductResponse;
import com.jorgelazo.product.entity.Product;
import com.jorgelazo.product.exception.ProductNotFoundException;
import com.jorgelazo.product.mapper.ProductMapper;
import com.jorgelazo.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProductResponse> findAll(){

        return repository.findAll()
        .stream()
        .map(mapper::toResponse)
        .toList();
    }

    public ProductResponse save(ProductRequest productRequest){

        Product product = mapper.toEntity(productRequest);
        
        Product savedProduct = repository.save(product);

        return mapper.toResponse(savedProduct);
    }

    public ProductResponse findById(Long id){

        Product product = getProduct(id);

        return mapper.toResponse(product);
    }

    public ProductResponse update(Long id, ProductRequest productRequest){

        Product product = getProduct(id);

        product.setName(productRequest.getName());
        product.setStock(productRequest.getStock());
        product.setPrice(productRequest.getPrice());

        Product updatedProduct = repository.save(product);

        return mapper.toResponse(updatedProduct);
    }

    public void delete(Long id){

        Product product = getProduct(id);

        repository.delete(product);
    }

    private Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " was not found"));
    }
}