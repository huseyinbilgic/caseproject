package com.caseproject.caseproject.mapper;

import org.springframework.stereotype.Component;
import com.caseproject.caseproject.entities.Order;
import com.caseproject.caseproject.response.OrderResponse;

@Component
public class OrderMapper {

    public OrderResponse orderToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .amount(order.getAmount())
                .price(order.getPrice())
                .totalPrice(order.getTotalPrice())
                .cartId(order.getCart().getId())
                .productId(order.getProduct().getId())
                .code(order.getCode())
                .build();
    }
}
