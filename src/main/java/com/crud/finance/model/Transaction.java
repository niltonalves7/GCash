package com.crud.finance.model;

import com.crud.finance.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Transfers")
public class Transaction {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id private UUID id;

    @ManyToOne
    private User user;

    private BigDecimal amount;
    private LocalDate date;
    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
