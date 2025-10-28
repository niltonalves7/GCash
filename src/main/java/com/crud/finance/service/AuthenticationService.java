package com.crud.finance.service;

import com.crud.finance.dto.request.LoginRequestDTO;
import com.crud.finance.dto.request.RegisterRequestDTO;
import com.crud.finance.dto.response.LoginResponseDTO;
import com.crud.finance.dto.response.RegisterResponseDTO;
import com.crud.finance.exceptions.ResourceNotFoundException;
import com.crud.finance.mapper.UserMapper;
import com.crud.finance.entity.User;
import com.crud.finance.repository.UserRepository;
import com.crud.finance.security.jwt.JwtService;
import com.crud.finance.validator.UserValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponseDTO registerUser(RegisterRequestDTO requestDTO) {
        UserValidator.validate(requestDTO);

        if (userRepository.existsByEmail(requestDTO.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        String accountNumber = generateUniqueAccountNumber();
        User user = UserMapper.toEntity(requestDTO);
        user.setAccountNumber(accountNumber);
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        userRepository.save(user);

        return UserMapper.toDTO(user);
    }

    public LoginResponseDTO login(LoginRequestDTO requestDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getEmail(),
                        requestDTO.getPassword()
                )
        );

        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(("User not found!")));

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
