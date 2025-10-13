package com.crud.finance.model;

import com.crud.finance.model.enums.TransferCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Transfers")
public class Transfer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private int id;

    public String name;
    public Double amount;
    public LocalDate date;
    public String description;

    @Enumerated(EnumType.STRING)
    public TransferCategory transferCategory;
}
