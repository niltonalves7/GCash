package com.crud.finance.service;

import com.crud.finance.dto.TransferRequestDTO;
import com.crud.finance.dto.TransferResponseDTO;
import com.crud.finance.model.Transfer;
import com.crud.finance.repository.TransferRepository;
import com.crud.finance.transferMapper.TransferMapper;
import com.crud.finance.validator.TransferValidator;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferValidator transferValidator;

    public TransferService(TransferRepository transferRepository, TransferValidator transferValidator) {
        this.transferRepository = transferRepository;
        this.transferValidator = transferValidator;
    }

    public TransferResponseDTO create(TransferRequestDTO dto){
        Transfer transfer = TransferMapper.toEntity(dto);
        transferValidator.validate(dto);
        transferRepository.save(transfer);
        return TransferMapper.toDTO(transfer);
    }
}
