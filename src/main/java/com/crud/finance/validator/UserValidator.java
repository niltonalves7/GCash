package com.crud.finance.validator;

import com.crud.finance.dto.request.RegisterRequestDTO;
import com.crud.finance.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component()
public class UserValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    public static void validate(RegisterRequestDTO dto){
        if(dto == null){
            throw new BadRequestException("fill in the required fields!");
        }

        if(dto.getName() == null || dto.getName().trim().isEmpty()){
            throw new BadRequestException("Name is required!");
        }

        if(dto.getEmail() == null || dto.getEmail().trim().isEmpty()){
            throw new BadRequestException(("Email is required!"));
        }

        if (!EMAIL_PATTERN.matcher(dto.getEmail()).matches()) {
            throw new BadRequestException("Invalid email");
        }

        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            throw new BadRequestException("Password must be at least 6 characters long");
        }
    }
}
