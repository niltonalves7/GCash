package com.crud.finance.service;

import com.crud.finance.dto.request.RegisterRequestDTO;
import com.crud.finance.dto.response.RegisterResponseDTO;
import com.crud.finance.exceptions.EmptyListException;
import com.crud.finance.exceptions.ResourceNotFoundException;
import com.crud.finance.mapper.UserMapper;
import com.crud.finance.model.User;
import com.crud.finance.repository.UserRepository;
import com.crud.finance.validator.UserValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            throw new EmptyListException("Empty list");
        }
        return users;
    }

    public RegisterResponseDTO getUserById(UUID id) {
        User getUserById = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return UserMapper.toDTO(getUserById);
    }

    public RegisterResponseDTO updateUser(UUID id, RegisterRequestDTO dto){
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
}
