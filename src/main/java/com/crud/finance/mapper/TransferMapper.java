package com.crud.finance.mapper;

import com.crud.finance.dto.request.TransferRequestDTO;
import com.crud.finance.dto.response.TransferResponseDTO;
import com.crud.finance.model.Transfer;

import java.time.LocalDate;

public class TransferMapper {

    public static TransferResponseDTO toDTO(Transfer transfer){
        if(transfer == null) return null;
        return new TransferResponseDTO(
                transfer.getName(),
                transfer.getAmount(),
                transfer.getDate()
        );
    }

    public static Transfer toEntity(TransferRequestDTO dto){
        if(dto == null) return null;
        Transfer transfer = new Transfer();
        transfer.setName(dto.getName());
        transfer.setAmount(dto.getAmount());
        transfer.setTransferType(dto.getTransferType());
        transfer.setDate(LocalDate.now());
        transfer.setDescription(dto.getDescription());
        return transfer;
    }
}
