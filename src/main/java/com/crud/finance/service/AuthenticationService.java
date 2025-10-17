package com.crud.finance.service;

import com.crud.finance.dto.request.LoginRequestDTO;
import com.crud.finance.dto.request.RegisterRequestDTO;
import com.crud.finance.dto.response.LoginResponseDTO;
import com.crud.finance.dto.response.RegisterResponseDTO;
import com.crud.finance.exceptions.ResourceNotFoundException;
import com.crud.finance.mapper.UserMapper;
import com.crud.finance.model.User;
import com.crud.finance.repository.UserRepository;
import com.crud.finance.security.jwt.JwtService;
import com.crud.finance.validator.UserValidator;
import com.crud.finance.exceptions.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegisterResponseDTO registerUser(RegisterRequestDTO dto) {
        UserValidator.validate(dto);

        if (userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        String accountNumber = generateUniqueAccountNumber();
        User user = UserMapper.toEntity(dto);
        user.setAccountNumber(accountNumber);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);

        return UserMapper.toDTO(user);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(("User not found!")));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDTO(token, refreshToken);
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = String.valueOf(ThreadLocalRandom.current().nextInt(10000000, 99999999));
        } while (userRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }
}
