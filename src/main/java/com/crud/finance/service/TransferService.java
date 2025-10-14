package com.crud.finance.service;

import com.crud.finance.dto.request.TransferRequestDTO;
import com.crud.finance.dto.response.TransferResponseDTO;
import com.crud.finance.exceptions.EmptyListException;
import com.crud.finance.exceptions.ResourceNotFoundException;
import com.crud.finance.model.Transfer;
import com.crud.finance.repository.TransferRepository;
import com.crud.finance.mapper.TransferMapper;
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

    public TransferResponseDTO getTransferById(Long id){
        Transfer transferById = transferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found with id " + id));
        return TransferMapper.toDTO(transferById);
    }

    public TransferResponseDTO updateTransfer(Long id, TransferRequestDTO dto){
        transferValidator.validate(dto);
        Transfer transferExist = transferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found with id " + id));

        transferExist.setName(dto.getName());
        transferExist.setAmount(dto.getAmount());
        transferExist.setTransferType(dto.getTransferType());
        transferExist.setDate(dto.getDate());
        transferExist.setDescription(dto.getDescription());

        Transfer transferUpdated = transferRepository.save(transferExist);
        return TransferMapper.toDTO(transferUpdated);
    }

    public void deleteTransfer(Long id){
        Transfer transferById = transferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found with id " + id));
        transferRepository.deleteById(id);
    }
}
