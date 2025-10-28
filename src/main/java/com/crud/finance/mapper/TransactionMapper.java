package com.crud.finance.mapper;

import com.crud.finance.dto.request.TransactionRequestDTO;
import com.crud.finance.dto.response.TransactionResponseDTO;
import com.crud.finance.entity.Transaction;

import java.time.LocalDate;

public class TransactionMapper {

    public static TransactionResponseDTO toDTO(Transaction transaction){
        if(transaction == null) return null;
        return new TransactionResponseDTO(
                transaction.getUser(),
                transaction.getAmount(),
                transaction.getDate()
        );
    }

    public static Transaction toEntity(TransactionRequestDTO dto){
        if(dto == null) return null;
        Transaction transaction = new Transaction();
        transaction.setUser(dto.getUser());
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setDate(LocalDate.now());
        transaction.setDescription(dto.getDescription());
        return transaction;
    }
}
