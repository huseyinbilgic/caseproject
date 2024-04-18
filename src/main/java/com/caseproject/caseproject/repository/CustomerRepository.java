package com.caseproject.caseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caseproject.caseproject.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
