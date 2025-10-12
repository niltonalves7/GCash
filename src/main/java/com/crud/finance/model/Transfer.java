package com.crud.finance.model;

import com.crud.finance.model.enums.TransferCategory;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    public String Name;
    public Double Amount;
    public LocalDate Date;
    public String Description;
    public TransferCategory TransferCategory;
}
