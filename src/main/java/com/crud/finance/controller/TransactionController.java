package com.crud.finance.controller;

import com.crud.finance.dto.request.TransactionRequestDTO;
import com.crud.finance.dto.response.TransactionResponseDTO;
import com.crud.finance.model.Transaction;
import com.crud.finance.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transfer")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public ResponseEntity<List<Transaction>> getAllTransfers(){
        return ResponseEntity.ok(transactionService.getAllTransfers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransferById(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.getTransferById(id));
    }

    @PostMapping()
    public ResponseEntity<TransactionResponseDTO> createTransfer(@RequestBody TransactionRequestDTO dto ){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransfer(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransfer(@PathVariable Long id, @RequestBody TransactionRequestDTO dto){
        return ResponseEntity.ok(transactionService.updateTransfer(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable Long id){
        transactionService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }
}
