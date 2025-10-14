package com.crud.finance.validator;

import com.crud.finance.dto.request.TransferRequestDTO;
import com.crud.finance.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

@Component()
public class TransferValidator {

    public void validate(TransferRequestDTO dto){
        if(dto.getName() == null){
            throw new BadRequestException("Name is required!");
        }

        if(dto.getAmount() <= 0){
            throw new BadRequestException("The value must be greater than zero!");
        }

        if(!dto.getTransferCategory().name().equals("DEPOSIT") && !dto.getTransferCategory().name().equals("WITHDROW")){
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
