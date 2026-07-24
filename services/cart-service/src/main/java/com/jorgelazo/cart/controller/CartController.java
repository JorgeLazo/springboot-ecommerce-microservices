package com.jorgelazo.cart.controller;

import com.jorgelazo.cart.dto.request.CartItemRequest;
import com.jorgelazo.cart.dto.response.CartItemResponse;
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

    @GetMapping
    public List<CartItemResponse> getAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<CartItemResponse> create(@Valid @RequestBody CartItemRequest cartItemRequest){

        CartItemResponse cartItemResponse = service.save(cartItemRequest);

        URI location = URI.create("/cart/" + cartItemResponse.getId());
        
        return ResponseEntity.created(location).body(cartItemResponse);
    }

    @GetMapping("/{id}")
    public CartItemResponse getById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public CartItemResponse update(@PathVariable("id") Long id, @Valid @RequestBody CartItemRequest cartItemRequest){
        return service.update(id, cartItemRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}