package com.caseproject.caseproject.requests;

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
public class RemoveProductFromCart {
    @NotNull(message = "Order Id cannot be null")
    private Long orderId;

    @NotNull(message = "Cart Id cannot be null")
    private Long cartId;
}
