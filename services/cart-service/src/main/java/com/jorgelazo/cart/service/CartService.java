package com.jorgelazo.cart.service;

import com.jorgelazo.cart.entity.CartItem;
import com.jorgelazo.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public List<CartItem> getAll() {
        return repository.findAll();
    }

    public CartItem add(CartItem item) {
        return repository.save(item);
    }
}