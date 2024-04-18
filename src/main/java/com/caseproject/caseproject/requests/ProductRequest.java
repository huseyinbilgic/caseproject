package com.caseproject.caseproject.requests;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ProductRequest {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @NotNull(message = "Stock cannot be null")
    private Integer stock;
}
