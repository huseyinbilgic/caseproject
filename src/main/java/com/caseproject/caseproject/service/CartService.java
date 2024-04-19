package com.caseproject.caseproject.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.caseproject.caseproject.entities.Cart;
import com.caseproject.caseproject.entities.Customer;
import com.caseproject.caseproject.entities.Order;
import com.caseproject.caseproject.exceptionHandlers.errors.NotFoundException;
import com.caseproject.caseproject.mapper.CartMapper;
import com.caseproject.caseproject.repository.CartRepository;
import com.caseproject.caseproject.repository.CustomerRepository;
import com.caseproject.caseproject.repository.OrderRepository;
import com.caseproject.caseproject.requests.CreateEmptyCart;
import com.caseproject.caseproject.requests.RemoveProductFromCart;
import com.caseproject.caseproject.response.CartResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final CartMapper cartMapper;

    public List<CartResponse> fetchCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cart -> cartMapper.cartToCartResponse(cart))
                .collect(Collectors.toList());
    }

    public CartResponse fetchCartById(Long id) {
        Optional<Cart> cartById = cartRepository.findById(id);

        if (cartById.isPresent()) {
            return cartMapper.cartToCartResponse(cartById.get());
        }

        throw new NotFoundException("Invalid Cart ID");
    }

    public CartResponse createCart(CreateEmptyCart createEmptyCart) {
        Optional<Customer> customerById = customerRepository.findById(createEmptyCart.getCustomerId());

        if (customerById.isPresent()) {
            Optional<Cart> cartByCustomer = cartRepository.findByCustomer(customerById.get());
            if (cartByCustomer.isPresent()) {
                return cartMapper.cartToCartResponse(cartByCustomer.get());
            }

            Cart cart = Cart.builder()
                    .customer(customerById.get())
                    .totalPrice(BigDecimal.ZERO)
                    .build();
            cartRepository.save(cart);
            return cartMapper.cartToCartResponse(cart);
        }

        throw new NotFoundException("Invalid Customer ID");
    }

    public CartResponse removeProductFromCart(RemoveProductFromCart removeProductFromCart) {
        Optional<Cart> cartById = cartRepository.findById(removeProductFromCart.getCartId());

        if (cartById.isPresent()) {
            Cart cart = cartById.get();

            Optional<Order> optionalOrder = cart.getOrders().stream()
                    .filter(order -> order.getId() == removeProductFromCart.getOrderId())
                    .findFirst();
            if (optionalOrder.isPresent()) {
                cart.getOrders().remove(optionalOrder.get());
                BigDecimal totalPrice = cart.getOrders().stream()
                        .map(order -> order.getTotalPrice())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                cart.setTotalPrice(totalPrice);
                cartRepository.save(cart);

                orderRepository.delete(optionalOrder.get());

                return cartMapper.cartToCartResponse(cart);
            }

            throw new NotFoundException("Invalid Order ID");
        }

        throw new NotFoundException("Invalid Cart ID");
    }
}
