package com.caseproject.caseproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caseproject.caseproject.entities.Cart;
import com.caseproject.caseproject.entities.Customer;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomer(Customer customer);
}
