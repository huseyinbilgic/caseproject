package com.caseproject.caseproject.mapper;

import org.springframework.stereotype.Component;

import com.caseproject.caseproject.entities.Customer;
import com.caseproject.caseproject.response.CustomerResponse;

@Component
public class CustomerMapper {

    public CustomerResponse customerToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }
}
