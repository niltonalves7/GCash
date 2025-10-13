package com.crud.finance.transferMapper;

import com.crud.finance.dto.TransferRequestDTO;
import com.crud.finance.dto.TransferResponseDTO;
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
        transfer.setTransferCategory(dto.getTransferCategory());
        transfer.setDate(LocalDate.now());
        transfer.setDescription(dto.getDescription());
        return transfer;
    }
}
