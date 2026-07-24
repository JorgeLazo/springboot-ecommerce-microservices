package com.jorgelazo.cart.config;

import com.jorgelazo.cart.entity.CartItem;
import com.jorgelazo.cart.repository.CartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CartRepository repository;

    public DataLoader(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        if (repository.count() == 0) {

            repository.save(new CartItem(
                    1L,
                    2
            ));

            repository.save(new CartItem(
                    2L,
                    1
            ));

            repository.save(new CartItem(
                    3L,
                    5
            ));

            repository.save(new CartItem(
                    4L,
                    3
            ));
        }
    }
}