package com.caseproject.caseproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.caseproject.caseproject.entities.Order;
import com.caseproject.caseproject.entities.Product;
import com.caseproject.caseproject.exceptionHandlers.errors.NotFoundException;
import com.caseproject.caseproject.mapper.ProductMapper;
import com.caseproject.caseproject.repository.OrderRepository;
import com.caseproject.caseproject.repository.ProductRepository;
import com.caseproject.caseproject.requests.ProductRequest;
import com.caseproject.caseproject.response.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> fetchAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> productMapper.productToProductResponse(product))
                .collect(Collectors.toList());
    }

    public ProductResponse fetchProductById(Long id) throws Exception {
        Optional<Product> productById = productRepository.findById(id);

        if (productById.isPresent()) {
            return productMapper.productToProductResponse(productById.get());
        }

        throw new NotFoundException("Invalid product ID");
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.productRequestToProduct(productRequest);
        productRepository.save(product);

        return productMapper.productToProductResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Optional<Product> productById = productRepository.findById(id);

        if (productById.isPresent()) {
            Product product = productMapper.productRequestToProduct(productRequest);
            product.setId(productById.get().getId());
            
            productRepository.save(product);

            return productMapper.productToProductResponse(product);
        }

        throw new NotFoundException("Invalid product ID");
    }

    public String deleteProduct(Long id) {
        Optional<Product> productById = productRepository.findById(id);

        if (productById.isPresent()) {
            List<Order> byProduct = orderRepository.findByProduct(productById.get());
            if (!byProduct.isEmpty()) {
                throw new NotFoundException("There are orders for this product. This product cannot be deleted.");
            }
            productRepository.delete(productById.get());
            return "Deleted with ID " + id;
        }

        throw new NotFoundException("Invalid product ID");
    }
}
