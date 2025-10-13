package com.crud.finance.dto;

import com.crud.finance.model.enums.TransferCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
    public String name;
    public Double amount;
    public TransferCategory transferCategory;
    public LocalDate date = LocalDate.now();
    public String description;
}
