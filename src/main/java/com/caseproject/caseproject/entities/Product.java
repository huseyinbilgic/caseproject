package com.caseproject.caseproject.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private Integer stock;
}
