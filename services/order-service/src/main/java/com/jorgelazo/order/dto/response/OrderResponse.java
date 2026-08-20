package com.jorgelazo.order.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.jorgelazo.order.entity.OrderStatus;

public class OrderResponse {

    private Long id;

    private Long userId;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private BigDecimal total;

    private List<OrderItemResponse> items;

    public OrderResponse() {
    }

    public OrderResponse(
            Long id,
            Long userId,
            OrderStatus status,
            LocalDateTime createdAt,
            BigDecimal total,
            List<OrderItemResponse> items) {

        this.id = id;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
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
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public List<OrderItemResponse> getItems() {
        return items;
    }
    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
}