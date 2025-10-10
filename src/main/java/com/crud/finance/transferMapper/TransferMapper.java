package com.crud.finance.transferMapper;

import com.crud.finance.dto.TransferRequestDTO;
import com.crud.finance.model.Transfer;

import java.time.LocalDate;

public class TransferMapper {

    public static TransferResponseDTO toDTO(Transfer transfer){
        TransferResponseDTO dto = new TransferResponseDTO();
        dto.setId(transfer.getId());
        dto.setName(transfer.getName());
        dto.setAmount(transfer.getAmount());
        dto.setCategory(transfer.getTransferCategory());
        dto.setDate(transfer.getDate().toString());
        dto.setDescription(transfer.getDescription());
        return dto;
    }

    public static TransferResponseDTO toEntity(TransferRequestDTO dto){
        Transfer transfer = new Transfer();
        transfer.setId(transfer.getId());
        transfer.setName(transfer.getName());
        transfer.setAmount(transfer.getAmount());
        transfer.setTransferCategory(transfer.getTransferCategory());
        transfer.setDate(LocalDate.parse(dto.getDate()));
        transfer.setDescription(transfer.getDescription());
        return transfer;
    }
}
