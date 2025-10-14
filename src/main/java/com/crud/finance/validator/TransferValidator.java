package com.crud.finance.validator;

import com.crud.finance.dto.request.TransferRequestDTO;
import org.springframework.stereotype.Component;

@Component()
public class TransferValidator {

    public void validate(TransferRequestDTO dto){
        if(dto.getName() == null){
            throw new IllegalArgumentException("Name is required!");
        }

        if(dto.getAmount() <= 0){
            throw new IllegalArgumentException("The value must be greater than zero!");
        }

        if(!dto.getTransferCategory().name().equals("DEPOSIT") && !dto.getTransferCategory().name().equals("WITHDROW")){
            throw new IllegalArgumentException(("Invalid Category"));
        }

        if(dto.getDate() == null){
            throw new IllegalArgumentException(("Date is requires"));
        }

        if(dto.getDescription() == null){
            throw new IllegalArgumentException("The description field is empty!");
        }
    }
}
