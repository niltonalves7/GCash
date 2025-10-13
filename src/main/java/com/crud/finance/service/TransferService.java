package com.crud.finance.service;

import com.crud.finance.dto.TransferRequestDTO;
import com.crud.finance.dto.TransferResponseDTO;
import com.crud.finance.exceptions.EmptyListException;
import com.crud.finance.model.Transfer;
import com.crud.finance.repository.TransferRepository;
import com.crud.finance.transferMapper.TransferMapper;
import com.crud.finance.validator.TransferValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferValidator transferValidator;

    public TransferService(TransferRepository transferRepository, TransferValidator transferValidator) {
        this.transferRepository = transferRepository;
        this.transferValidator = transferValidator;
    }

    public TransferResponseDTO createTransfer(TransferRequestDTO dto){
        transferValidator.validate(dto);
        Transfer transfer = TransferMapper.toEntity(dto);
        Transfer saved = transferRepository.save(transfer);
        return TransferMapper.toDTO(saved);
    }

    public List<Transfer> getAllTransfers(){
        List<Transfer> transfers = transferRepository.findAll();
        if(transfers.isEmpty()){
            throw new EmptyListException("Empty list");
        }
        return transfers;
    }
}
