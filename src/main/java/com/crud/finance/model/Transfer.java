package com.crud.finance.model;

import com.crud.finance.model.enums.TransferType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Transfers")
public class Transfer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private int id;

    private String name;
    private BigDecimal amount;
    private LocalDate date;
    private String description;

    @Enumerated(EnumType.STRING)
    private TransferType transferType;
}
