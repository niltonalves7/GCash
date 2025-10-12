package com.crud.finance.controller;

import com.crud.finance.dto.TransferRequestDTO;
import com.crud.finance.dto.TransferResponseDTO;
import com.crud.finance.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferResponseDTO> create(@RequestBody TransferRequestDTO dto) {
        TransferResponseDTO response = transferService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
