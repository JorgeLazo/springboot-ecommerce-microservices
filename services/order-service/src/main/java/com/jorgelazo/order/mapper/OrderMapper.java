package com.jorgelazo.order.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jorgelazo.order.dto.response.OrderItemResponse;
import com.jorgelazo.order.dto.response.OrderResponse;
import com.jorgelazo.order.entity.Order;
import com.jorgelazo.order.entity.OrderItem;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {

        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(this::toItemResponse)
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getTotal(),
                items);
    }

    private OrderItemResponse toItemResponse(OrderItem item) {

        return new OrderItemResponse(
                item.getProductId(),
                item.getProductName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getSubtotal());
    }

}
