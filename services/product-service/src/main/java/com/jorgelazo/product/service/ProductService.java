package com.jorgelazo.product.service;

import com.jorgelazo.common.exception.ResourceNotFoundException;
import com.jorgelazo.product.dto.request.ProductRequest;
import com.jorgelazo.product.dto.response.ProductResponse;
import com.jorgelazo.product.entity.Product;
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

        product.setSku(productRequest.getSku());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setBrand(productRequest.getBrand());
        product.setCategory(productRequest.getCategory());
        product.changePrice(productRequest.getPrice());
        product.updateStock(productRequest.getStock());
        product.activate();

        Product updatedProduct = repository.save(product);

        return mapper.toResponse(updatedProduct);
    }

    public void delete(Long id){

        Product product = getProduct(id);

        repository.delete(product);
    }

    private Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }
}