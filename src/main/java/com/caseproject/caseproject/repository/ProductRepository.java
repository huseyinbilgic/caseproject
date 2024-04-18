package com.caseproject.caseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caseproject.caseproject.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
