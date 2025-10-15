package com.crud.finance.mapper;

import com.crud.finance.dto.request.UserRequestDTO;
import com.crud.finance.dto.response.UserResponseDTO;
import com.crud.finance.model.User;

import java.math.BigDecimal;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
        if (user == null) return null;

        String accountNumber = null;
        BigDecimal balance = BigDecimal.ZERO;

        if(user.getAccount() != null) {
            accountNumber = user.getAccount().getAccountNumber();
            balance = user.getAccount().getBalance();
        }

        return new UserResponseDTO(
                user.getName(),
                user.getEmail(),
                accountNumber,
                balance
        );
    }
}
