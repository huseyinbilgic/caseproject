package com.caseproject.caseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caseproject.caseproject.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
