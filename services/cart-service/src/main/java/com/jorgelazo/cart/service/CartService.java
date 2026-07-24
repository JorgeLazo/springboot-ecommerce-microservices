package com.jorgelazo.cart.service;

import com.jorgelazo.cart.dto.request.CartItemRequest;
import com.jorgelazo.cart.dto.response.CartItemResponse;
import com.jorgelazo.cart.entity.CartItem;
import com.jorgelazo.cart.exception.CartItemNotFoundException;
import com.jorgelazo.cart.mapper.CartItemMapper;
import com.jorgelazo.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository repository;
    private final CartItemMapper mapper;

    public CartService(CartRepository repository, CartItemMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CartItemResponse> findAll(){

        return repository.findAll()
        .stream()
        .map(mapper::toResponse)
        .toList();
    }

    public CartItemResponse save(CartItemRequest cartItemRequest){

        CartItem cartItem = mapper.toEntity(cartItemRequest);
        
        CartItem savedCartItem = repository.save(cartItem);

        return mapper.toResponse(savedCartItem);
    }

    public CartItemResponse findById(Long id){

        CartItem cartItem = getCartItem(id);

        return mapper.toResponse(cartItem);
    }

    public CartItemResponse update(Long id, CartItemRequest cartItemRequest){

        CartItem cartItem = getCartItem(id);

        cartItem.setProductId(cartItemRequest.getProductId());
        cartItem.setQuantity(cartItemRequest.getQuantity());
        
        CartItem updatedCartItem = repository.save(cartItem);

        return mapper.toResponse(updatedCartItem);
    }

    public void delete(Long id){

        CartItem cartItem = getCartItem(id);

        repository.delete(cartItem);
    }

    private CartItem getCartItem(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item with id: " + id + " was not found"));
    } 
}