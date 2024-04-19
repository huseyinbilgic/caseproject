package com.caseproject.caseproject.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caseproject.caseproject.entities.Cart;
import com.caseproject.caseproject.entities.Customer;
import com.caseproject.caseproject.entities.Order;
import com.caseproject.caseproject.entities.Product;
import com.caseproject.caseproject.exceptionHandlers.errors.NotFoundException;
import com.caseproject.caseproject.mapper.CartMapper;
import com.caseproject.caseproject.mapper.OrderMapper;
import com.caseproject.caseproject.repository.CartRepository;
import com.caseproject.caseproject.repository.CustomerRepository;
import com.caseproject.caseproject.repository.OrderRepository;
import com.caseproject.caseproject.repository.ProductRepository;
import com.caseproject.caseproject.requests.PlaceOrderRequest;
import com.caseproject.caseproject.response.CartResponse;
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
    private final CartMapper cartMapper;

    public CartResponse placeOrder(PlaceOrderRequest placeOrderRequest) {
        Optional<Cart> cartById = cartRepository.findById(placeOrderRequest.getCartId());
        Optional<Product> productById = productRepository.findById(placeOrderRequest.getProductId());

        if (cartById.isPresent() && productById.isPresent()) {
            if (productById.get().getStock() < placeOrderRequest.getAmount()) {
                throw new NotFoundException("Out of Stock");
            }

            cartById.get().getOrders().stream().filter(order -> order.getProduct().getId() == productById.get().getId()
                    && order.getPrice().compareTo(productById.get().getPrice()) == 0)
                    .findFirst()
                    .ifPresentOrElse(order -> {
                        order.setAmount(order.getAmount() + placeOrderRequest.getAmount());
                        order.setTotalPrice(order.getPrice().multiply(BigDecimal.valueOf(order.getAmount())));
                    },
                            () -> {
                                Order order = Order.builder()
                                        .code(UUID.randomUUID().toString())
                                        .product(productById.get())
                                        .price(productById.get().getPrice())
                                        .amount(placeOrderRequest.getAmount())
                                        .totalPrice(
                                                productById.get().getPrice()
                                                        .multiply(BigDecimal.valueOf(placeOrderRequest.getAmount())))
                                        .cart(cartById.get())
                                        .build();

                                cartById.get().getOrders().add(order);

                                orderRepository.save(order);
                            });

            Product product = productById.get();
            product.setStock(product.getStock() - placeOrderRequest.getAmount());
            productRepository.save(product);

            cartById.get().setTotalPrice(updateCartTotalPrice(cartById.get().getOrders()));
            Cart savedCart = cartRepository.save(cartById.get());

            return cartMapper.cartToCartResponse(savedCart);

        }

        throw new NotFoundException("Invalid Cart ID or Product ID");
    }

    public BigDecimal updateCartTotalPrice(List<Order> orders) {
        return orders.stream()
                .map(order -> order.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderResponse> findAllByCustomerId(Long customerId) {
        Optional<Customer> customerById = customerRepository.findById(customerId);

        if (customerById.isPresent()) {
            Optional<Cart> cartByCustomer = cartRepository.findByCustomer(customerById.get());

            return cartByCustomer.isPresent()
                    ? cartByCustomer.get().getOrders().stream()
                            .map(order -> orderMapper.orderToOrderResponse(order))
                            .collect(Collectors.toList())
                    : new ArrayList<>();
        }
        throw new NotFoundException("Invalid Customer ID");
    }

    public OrderResponse findByCode(String code) {
        Optional<Order> byCode = orderRepository.findByCode(code);

        if (byCode.isPresent()) {
            return orderMapper.orderToOrderResponse(byCode.get());
        }

        throw new NotFoundException("Invalid Order Code");
    }
}
