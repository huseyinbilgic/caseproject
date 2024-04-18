package com.caseproject.caseproject.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caseproject.caseproject.requests.ProductRequest;
import com.caseproject.caseproject.response.ProductResponse;
import com.caseproject.caseproject.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> fetchAllProducts(){
        return ResponseEntity.ok(productService.fetchAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> fetchProductById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(productService.fetchProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
