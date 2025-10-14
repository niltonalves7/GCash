package com.crud.finance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDTO {
    private String name;
    private BigDecimal amount;
    private LocalDate date = LocalDate.now();
}
