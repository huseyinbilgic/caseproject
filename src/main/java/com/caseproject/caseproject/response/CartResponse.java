package com.caseproject.caseproject.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartResponse {
    private Long id;
    private List<OrderResponse> orders;
    private CustomerResponse customer;
    private BigDecimal totalPrice;
}
