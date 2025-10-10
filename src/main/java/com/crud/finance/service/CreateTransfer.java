package com.crud.finance.service;

import com.crud.finance.dto.TransferRequestDTO;
import com.crud.finance.model.Transfer;
import com.crud.finance.repository.TransferRepository;
import com.crud.finance.validator.TransferValidator;
import org.springframework.stereotype.Service;

@Service
public class CreateTransfer {

    private final TransferRepository transferRepository;
    private final TransferValidator transferValidator;

    public CreateTransfer(TransferRepository transferRepository, TransferValidator transferValidator) {
        this.transferRepository = transferRepository;
        this.transferValidator = transferValidator;
    }

    public Transfer save(TransferRequestDTO dto){
        transferValidator.validate(dto);
        Transfer transfer = TransferMapper.toEntiry(dto);
        return transferRepository.save(transfer);
    }
}
