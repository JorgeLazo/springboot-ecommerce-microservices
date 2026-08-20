package com.jorgelazo.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorgelazo.cart.entity.Cart;
import com.jorgelazo.cart.entity.CartStatus;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserIdAndStatus(Long userId, CartStatus status);
}