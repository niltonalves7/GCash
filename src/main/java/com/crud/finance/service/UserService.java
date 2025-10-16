package com.crud.finance.service;

import com.crud.finance.dto.request.UserRequestDTO;
import com.crud.finance.dto.response.UserResponseDTO;
import com.crud.finance.exceptions.EmptyListException;
import com.crud.finance.exceptions.ResourceNotFoundException;
import com.crud.finance.mapper.UserMapper;
import com.crud.finance.model.User;
import com.crud.finance.repository.UserRepository;
import com.crud.finance.validator.UserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            throw new EmptyListException("Empty list");
        }
        return users;
    }

    public UserResponseDTO getUserById(UUID id) {
        User getUserById = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return UserMapper.toDTO(getUserById);
    }

    public UserResponseDTO registerUser(UserRequestDTO dto) {
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

    public UserResponseDTO updateUser(UUID id, UserRequestDTO dto){
        UserValidator.validate(dto);
        User userExist = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        userExist.setName(dto.getName());
        userExist.setEmail(dto.getEmail());
        userExist.setPassword(dto.getPassword());

        User userUpdated = userRepository.save(userExist);
        return UserMapper.toDTO(userUpdated);
    }

    public void deleteUser(UUID id){
        User userById = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.deleteById(id);
    }

    public User findByAccountNumber(String accountNumber) {
        return userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found."));
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = String.valueOf(ThreadLocalRandom.current().nextInt(10000000, 99999999));
        } while (userRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }
}
