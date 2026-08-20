package com.jorgelazo.cart.controller;

import com.jorgelazo.cart.dto.request.AddItemRequest;
import com.jorgelazo.cart.dto.request.CartItemRequest;
import com.jorgelazo.cart.dto.response.CartItemResponse;
import com.jorgelazo.cart.entity.Cart;
import com.jorgelazo.cart.service.CartService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service){
        this.service = service;
    }

   
    @PostMapping("/items")   
    @ResponseStatus(HttpStatus.CREATED)
    public Cart addItem(@Valid @RequestBody AddItemRequest request) {
        return service.addItem(request);
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public Cart removeItem(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        return service.removeItem(userId, productId);
    }

    @PutMapping("/{userId}/items/{productId}")
public Cart updateQuantity(
        @PathVariable Long userId,
        @PathVariable Long productId,
        @RequestParam Integer quantity) {

    return service.updateQuantity(userId, productId, quantity);
}

@GetMapping("/{userId}")
public Cart getActiveCart(@PathVariable Long userId){

    return service.getActiveCart(userId);
}

@DeleteMapping("/{userId}/items")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void clearCart(
        @PathVariable Long userId){

    service.clearCart(userId);
}

@PostMapping("/{userId}/checkout")
public String checkout(
        @PathVariable Long userId){

    return service.checkout(userId);
}
    
}