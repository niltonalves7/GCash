package com.crud.finance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id private UUID id;

    private String name;
    private String password;
    private String email;
    private String accountNumber;
    private BigDecimal balance = BigDecimal.ZERO;
}
