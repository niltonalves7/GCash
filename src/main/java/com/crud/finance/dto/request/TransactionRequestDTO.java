package com.crud.finance.dto.request;

import com.crud.finance.entity.User;
import com.crud.finance.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {
    private User user;
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDate date = LocalDate.now();
    private String description;
}
