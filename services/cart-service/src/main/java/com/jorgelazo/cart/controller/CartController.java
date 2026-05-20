package com.jorgelazo.cart.controller;

import com.jorgelazo.cart.entity.CartItem;
import com.jorgelazo.cart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping
    public List<CartItem> all() {
        return service.getAll();
    }

    @PostMapping
    public CartItem add(
            @RequestBody CartItem item
    ) {
        return service.add(item);
    }
}