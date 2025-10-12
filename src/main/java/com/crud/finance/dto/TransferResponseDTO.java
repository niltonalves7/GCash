package com.crud.finance.dto;

import com.crud.finance.model.enums.TransferCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDTO {
    public String Name;
    public Double Amount;
    public TransferCategory TransferCategory;
    public LocalDate Date = LocalDate.now();
    public String Description;
}
