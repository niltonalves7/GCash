package com.crud.finance.dto.response;

import com.crud.finance.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private User user;
    private BigDecimal amount;
    private LocalDate date = LocalDate.now();
}
