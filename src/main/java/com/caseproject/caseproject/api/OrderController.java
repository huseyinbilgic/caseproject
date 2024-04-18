package com.caseproject.caseproject.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caseproject.caseproject.requests.PlaceOrderRequest;
import com.caseproject.caseproject.response.OrderResponse;
import com.caseproject.caseproject.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    
    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody @Valid PlaceOrderRequest placeOrderRequest) {
        return ResponseEntity.ok(orderService.placeOrder(placeOrderRequest));
    }

    @GetMapping("/byCustomer/{customerId}")
    public ResponseEntity<List<OrderResponse>> findAllByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.findAllByCustomerId(customerId));
    }
    
}
