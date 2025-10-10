package com.crud.finance.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    public String Name;
    public Double Amount;
    public Category Category;
    public LocalDate Date = LocalDate.now();
    public String Description;
}
