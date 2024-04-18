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
public class CreateEmptyCart {
    
    @NotNull(message = "Customer Id cannot be null")
    private Long customerId;
}
