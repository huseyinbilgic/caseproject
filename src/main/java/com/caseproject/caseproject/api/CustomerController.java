package com.caseproject.caseproject.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caseproject.caseproject.requests.AddCustomerRequest;
import com.caseproject.caseproject.response.CustomerResponse;
import com.caseproject.caseproject.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody AddCustomerRequest addCustomerRequest) {
        return ResponseEntity.ok(customerService.addCustomer(addCustomerRequest));
    }
}
