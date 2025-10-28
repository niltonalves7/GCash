package com.crud.finance.controller;

import com.crud.finance.dto.request.TransactionRequestDTO;
import com.crud.finance.dto.response.TransactionResponseDTO;
import com.crud.finance.entity.Transaction;
import com.crud.finance.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransferById(@PathVariable UUID id){
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PostMapping()
    public ResponseEntity<TransactionResponseDTO> createTransfer(@RequestBody TransactionRequestDTO dto ){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransfer(@PathVariable UUID id, @RequestBody TransactionRequestDTO dto){
        return ResponseEntity.ok(transactionService.updateTransaction(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable UUID id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
