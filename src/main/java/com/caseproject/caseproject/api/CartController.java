package com.caseproject.caseproject.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caseproject.caseproject.requests.CreateEmptyCart;
import com.caseproject.caseproject.requests.RemoveProductFromCart;
import com.caseproject.caseproject.response.CartResponse;
import com.caseproject.caseproject.service.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartResponse>> fetchCarts() {
        return ResponseEntity.ok(cartService.fetchCarts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> fetchCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.fetchCartById(id));
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@RequestBody @Valid CreateEmptyCart createEmptyCart) {
        return ResponseEntity.ok(cartService.createCart(createEmptyCart));
    }

    @PutMapping("/removeProductFromCart")
    public ResponseEntity<CartResponse> removeProductFromCart(
            @RequestBody @Valid RemoveProductFromCart removeProductFromCart) {
        return ResponseEntity.ok(cartService.removeProductFromCart(removeProductFromCart));
    }
}
