package com.crud.finance.service;

import com.crud.finance.dto.request.TransactionRequestDTO;
import com.crud.finance.dto.response.TransactionResponseDTO;
import com.crud.finance.exceptions.EmptyListException;
import com.crud.finance.exceptions.ResourceNotFoundException;
import com.crud.finance.model.Transaction;
import com.crud.finance.repository.TransactionRepository;
import com.crud.finance.mapper.TransactionMapper;
import com.crud.finance.validator.TransactionValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository, TransactionValidator transactionValidator) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponseDTO createTransfer(TransactionRequestDTO dto){
        TransactionValidator.validate(dto);
        Transaction transaction = TransactionMapper.toEntity(dto);
        Transaction saved = transactionRepository.save(transaction);
        return TransactionMapper.toDTO(saved);
    }

    public List<Transaction> getAllTransfers(){
        List<Transaction> transactions = transactionRepository.findAll();
        if(transactions.isEmpty()){
            throw new EmptyListException("Empty list");
        }
        return transactions;
    }

    public TransactionResponseDTO getTransferById(Long id){
        Transaction transactionById = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found with id " + id));
        return TransactionMapper.toDTO(transactionById);
    }

    public TransactionResponseDTO updateTransfer(Long id, TransactionRequestDTO dto){
        TransactionValidator.validate(dto);
        Transaction transactionExist = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found with id " + id));

        transactionExist.setUser(dto.getUser());
        transactionExist.setAmount(dto.getAmount());
        transactionExist.setTransactionType(dto.getTransactionType());
        transactionExist.setDate(dto.getDate());
        transactionExist.setDescription(dto.getDescription());

        Transaction transactionUpdated = transactionRepository.save(transactionExist);
        return TransactionMapper.toDTO(transactionUpdated);
    }

    public void deleteTransfer(Long id){
        Transaction transactionById = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found with id " + id));
        transactionRepository.deleteById(id);
    }
}
