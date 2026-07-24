package com.jorgelazo.product.controller;

import com.jorgelazo.product.dto.request.ProductRequest;
import com.jorgelazo.product.dto.response.ProductResponse;
import com.jorgelazo.product.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping
    public List<ProductResponse> getAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest){

        ProductResponse productResponse = service.save(productRequest);

        URI location = URI.create("/products/" + productResponse.getId());
        
        return ResponseEntity.created(location).body(productResponse);
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable("id") Long id, @Valid @RequestBody ProductRequest productRequest){
        return service.update(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }

}