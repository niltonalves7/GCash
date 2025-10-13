package com.crud.finance.controller;

import com.crud.finance.dto.TransferRequestDTO;
import com.crud.finance.dto.TransferResponseDTO;
import com.crud.finance.model.Transfer;
import com.crud.finance.service.TransferService;
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
    public List<Transfer> getAllTransfers(){
        return transferService.getAllTransfers();
    }

    @PostMapping()
    public TransferResponseDTO createTransfer(@RequestBody TransferRequestDTO dto ){
        return transferService.createTransfer(dto);
    }
}
