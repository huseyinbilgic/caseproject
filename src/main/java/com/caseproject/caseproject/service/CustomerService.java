package com.caseproject.caseproject.service;

import org.springframework.stereotype.Service;

import com.caseproject.caseproject.entities.Customer;
import com.caseproject.caseproject.mapper.CustomerMapper;
import com.caseproject.caseproject.repository.CustomerRepository;
import com.caseproject.caseproject.requests.AddCustomerRequest;
import com.caseproject.caseproject.response.CustomerResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse addCustomer(AddCustomerRequest addCustomerRequest) {
        Customer customer = Customer.builder()
                .name(addCustomerRequest.getName())
                .build();
        customerRepository.save(customer);

        return customerMapper.customerToCustomerResponse(customer);
    }

}
