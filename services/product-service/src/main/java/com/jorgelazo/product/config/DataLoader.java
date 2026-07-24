package com.jorgelazo.product.config;

import com.jorgelazo.product.entity.Product;
import com.jorgelazo.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository repository;

    public DataLoader(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        if (repository.count() == 0) {

            repository.save(new Product(
                    "MacBook Pro",
                    1599.99,
                    10
            ));

            repository.save(new Product(
                    "iPhone 16",
                    999.99,
                    25
            ));

            repository.save(new Product(
                    "Samsung Galaxy S26",
                    1099.99,
                    15
            ));

            repository.save(new Product(
                    "Sony WH-1000XM6",
                    399.99,
                    5
            ));
        }
    }
}