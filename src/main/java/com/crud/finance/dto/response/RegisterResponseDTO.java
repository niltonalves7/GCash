package com.crud.finance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    private String name;
    private String email;
    private String accountNumber;
    private BigDecimal balance;
}
