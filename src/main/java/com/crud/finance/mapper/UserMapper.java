package com.crud.finance.mapper;

import com.crud.finance.dto.request.RegisterRequestDTO;
import com.crud.finance.dto.response.RegisterResponseDTO;
import com.crud.finance.model.User;

public class UserMapper {

    public static User toEntity(RegisterRequestDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static RegisterResponseDTO toDTO(User user) {
        if (user == null) return null;

        return new RegisterResponseDTO(
                user.getName(),
                user.getEmail(),
                user.getAccountNumber(),
                user.getBalance()
        );
    }
}
