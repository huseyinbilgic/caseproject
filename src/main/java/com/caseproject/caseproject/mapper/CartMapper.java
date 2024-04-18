package com.caseproject.caseproject.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.caseproject.caseproject.entities.Cart;
import com.caseproject.caseproject.entities.Order;
import com.caseproject.caseproject.response.CartResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CartMapper {
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;

    public CartResponse cartToCartResponse(Cart cart) {
        List<Order> orders = cart.getOrders() != null ? cart.getOrders() : new ArrayList<>();
        return CartResponse.builder()
                .id(cart.getId())
                .orders(orders.stream()
                        .map(order -> orderMapper.orderToOrderResponse(order))
                        .collect(Collectors.toList()))
                .customer(customerMapper.customerToCustomerResponse(cart.getCustomer()))
                .totalPrice(cart.getTotalPrice())
                .build();
    }
}
