package com.caseproject.caseproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caseproject.caseproject.entities.Order;
import com.caseproject.caseproject.entities.Product;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByProduct(Product product);
}
