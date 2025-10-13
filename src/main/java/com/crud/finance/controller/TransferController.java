package com.crud.finance.controller;

import com.crud.finance.dto.TransferRequestDTO;
import com.crud.finance.dto.TransferResponseDTO;
import com.crud.finance.model.Transfer;
import com.crud.finance.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transfer")
@CrossOrigin(origins = "*")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping()
    public ResponseEntity<List<Transfer>> getAllTransfers(){
        return ResponseEntity.ok(transferService.getAllTransfers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> getTransferById(@PathVariable Long id){
        return ResponseEntity.ok(transferService.getTransferById(id));
    }

    @PostMapping()
    public ResponseEntity<TransferResponseDTO> createTransfer(@RequestBody TransferRequestDTO dto ){
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(dto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> updateTransfer(@PathVariable Long id, @RequestBody TransferRequestDTO dto){
        return ResponseEntity.ok(transferService.updateTransfer(id, dto));
    }
}
