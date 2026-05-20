package com.jorgelazo.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorgelazo.cart.entity.CartItem;

public interface CartRepository
        extends JpaRepository<CartItem, Long> {
}