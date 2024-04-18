package com.caseproject.caseproject.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caseproject.caseproject.entities.Cart;
import com.caseproject.caseproject.entities.Customer;
import com.caseproject.caseproject.entities.Order;
import com.caseproject.caseproject.entities.Product;
import com.caseproject.caseproject.exceptionHandlers.errors.NotFoundException;
import com.caseproject.caseproject.mapper.OrderMapper;
import com.caseproject.caseproject.repository.CartRepository;
import com.caseproject.caseproject.repository.CustomerRepository;
import com.caseproject.caseproject.repository.OrderRepository;
import com.caseproject.caseproject.repository.ProductRepository;
import com.caseproject.caseproject.requests.PlaceOrderRequest;
import com.caseproject.caseproject.response.OrderResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {
        Optional<Cart> cartById = cartRepository.findById(placeOrderRequest.getCartId());
        Optional<Product> productById = productRepository.findById(placeOrderRequest.getProductId());

        if (cartById.isPresent() && productById.isPresent()) {
            if (productById.get().getStock() < placeOrderRequest.getAmount()) {
                throw new NotFoundException("There are not enough products in stock.");
            }

            Order order = Order.builder()
                    .product(productById.get())
                    .price(productById.get().getPrice())
                    .amount(placeOrderRequest.getAmount())
                    .cart(cartById.get())
                    .build();
            cartById.get().getOrders().add(order);

            orderRepository.save(order);

            Product product = productById.get();
            product.setStock(product.getStock() - placeOrderRequest.getAmount());
            productRepository.save(product);

            cartById.get().setTotalPrice(updateCartTotalPrice(cartById.get().getOrders()));
            cartRepository.save(cartById.get());

            return orderMapper.orderToOrderResponse(order);

        }

        throw new NotFoundException("Invalid Cart ID or Product ID");
    }

    public BigDecimal updateCartTotalPrice(List<Order> orders) {
        return orders.stream()
                .map(order -> BigDecimal.valueOf(order.getAmount()).multiply(order.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderResponse> findAllByCustomerId(Long customerId) {
        Optional<Customer> customerById = customerRepository.findById(customerId);

        if (customerById.isPresent()) {
            List<Cart> byCustomer = cartRepository.findByCustomer(customerById.get());

            return byCustomer.stream().map(cart -> cart.getOrders()).flatMap(List::stream)
                    .map(order -> orderMapper.orderToOrderResponse(order))
                    .collect(Collectors.toList());
        }
        throw new NotFoundException("Invalid Customer ID");
    }
}
