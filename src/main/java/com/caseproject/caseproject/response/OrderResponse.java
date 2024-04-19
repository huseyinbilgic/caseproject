package com.caseproject.caseproject.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponse {
    private Long id;
    private Integer amount;
    private BigDecimal price;
    private Long cartId;
    private Long productId;
    private BigDecimal totalPrice;
    private String code;
}
