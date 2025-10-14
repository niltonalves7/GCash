package com.crud.finance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDTO {
    public String name;
    public Double amount;
    public LocalDate date = LocalDate.now();
}
