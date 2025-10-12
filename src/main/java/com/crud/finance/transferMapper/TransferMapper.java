package com.crud.finance.transferMapper;

import com.crud.finance.dto.TransferRequestDTO;
import com.crud.finance.dto.TransferResponseDTO;
import com.crud.finance.model.Transfer;
import com.crud.finance.model.enums.TransferCategory;

import java.time.LocalDate;

public class TransferMapper {

    public static TransferResponseDTO toDTO(Transfer transfer){
        TransferResponseDTO dto = new TransferResponseDTO();
        dto.setName(transfer.getName());
        dto.setAmount(transfer.getAmount());
        dto.setTransferCategory(transfer.getTransferCategory());
        dto.setDate(LocalDate.now());
        dto.setDescription(transfer.getDescription());
        return dto;
    }

    public static Transfer toEntity(TransferRequestDTO dto){
        Transfer transfer = new Transfer();
        transfer.setId(transfer.getId());
        transfer.setName(transfer.getName());
        transfer.setAmount(transfer.getAmount());
        transfer.setTransferCategory(TransferCategory.valueOf(dto.getTransferCategory().name()));
        transfer.setDate(LocalDate.now());
        transfer.setDescription(transfer.getDescription());
        return transfer;
    }
}
