package com.caseproject.caseproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    private String name;
}
