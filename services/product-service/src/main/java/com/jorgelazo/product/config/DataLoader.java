package com.jorgelazo.product.config;

import com.jorgelazo.product.entity.Product;
import com.jorgelazo.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jorgelazo.product.entity.ProductStatus;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository repository;

    public DataLoader(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        if (repository.count() == 0) {

            repository.save(
                new Product("SKU001", "Product 1", "Description 1", "Brand A", "Category X", 10.0, 100, ProductStatus.ACTIVE));
            repository.save(
                new Product("SKU002", "Product 2", "Description 2", "Brand B", "Category Y", 20.0, 200, ProductStatus.ACTIVE));
            repository.save(
                new Product("SKU003", "Product 3", "Description 3", "Brand C", "Category Z", 30.0, 300, ProductStatus.INACTIVE));

        }
    }
}