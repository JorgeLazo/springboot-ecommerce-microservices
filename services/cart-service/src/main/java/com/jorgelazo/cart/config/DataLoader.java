package com.jorgelazo.cart.config;

import com.jorgelazo.cart.entity.Cart;
import com.jorgelazo.cart.entity.CartItem;
import com.jorgelazo.cart.entity.CartStatus;
import com.jorgelazo.cart.repository.CartRepository;

import java.math.BigDecimal;

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

            Cart cart = new Cart(
                1L,
                CartStatus.ACTIVE,
                BigDecimal.ZERO
            );

            cart.addItem(
                new CartItem(
                    1L,
                    "Product A",
                    BigDecimal.valueOf(10.0),
                    2
                )
            );

        repository.save(cart);
        }
    }
}