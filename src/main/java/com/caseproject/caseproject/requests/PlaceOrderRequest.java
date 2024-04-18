package com.caseproject.caseproject.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequest {

    @NotNull(message = "Product Id cannot be null")
    private Long productId;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be at least 1")
    private Integer amount;

    @NotNull(message = "Cart Id cannot be null")
    private Long cartId;
    
}
