package com.crud.finance.controller;

import com.crud.finance.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dto) {
        User user = user.Repository.findByEmail(dto.getEmaiil());
    }
}
