package com.crud.finance.validator;

import com.crud.finance.dto.request.TransactionRequestDTO;
import com.crud.finance.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component()
public class TransactionValidator {

    public static void validate(TransactionRequestDTO dto){
        if(dto.getUser() == null){
            throw new BadRequestException("Name is required!");
        }

        if(dto.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("The value must be greater than zero!");
        }

        if(!dto.getTransactionType().name().equals("DEPOSIT") && !dto.getTransactionType().name().equals("WITHDROW")){
            throw new BadRequestException(("Invalid Category"));
        }

        if(dto.getDate() == null){
            throw new BadRequestException(("Date is requires"));
        }

        if(dto.getDescription() == null){
            throw new BadRequestException("The description field is empty!");
        }
    }
}
