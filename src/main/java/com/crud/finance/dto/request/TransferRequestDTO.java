package com.crud.finance.dto.request;

import com.crud.finance.model.enums.TransferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
    private String name;
    private BigDecimal amount;
    private TransferType transferType;
    private LocalDate date = LocalDate.now();
    private String description;
}
