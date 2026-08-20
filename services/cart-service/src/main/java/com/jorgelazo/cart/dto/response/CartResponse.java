package com.jorgelazo.cart.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class CartResponse {

    private Long id;
    private Long userId;
    private BigDecimal total;
    private List<CartItemResponse> items;

    public CartResponse() {
    }

    public CartResponse(Long id,
                        Long userId,
                        BigDecimal total,
                        List<CartItemResponse> items) {

        this.id = id;
        this.userId = userId;
        this.total = total;
        this.items = items;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public List<CartItemResponse> getItems() {
        return items;
    }
    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }
    
}