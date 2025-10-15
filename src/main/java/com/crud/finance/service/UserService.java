package com.crud.finance.service;

import com.crud.finance.dto.request.UserRequestDTO;
import com.crud.finance.dto.response.UserResponseDTO;
import com.crud.finance.exceptions.EmptyListException;
import com.crud.finance.mapper.UserMapper;
import com.crud.finance.model.Account;
import com.crud.finance.model.Transaction;
import com.crud.finance.model.User;
import com.crud.finance.repository.UserRepository;
import com.crud.finance.validator.UserValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    public UserService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new EmptyListException("Empty list");
        }
        return users;
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {
        UserValidator.validate(dto);

        if (userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        User user = UserMapper.toEntity(dto);
        userRepository.save(user);

        Account account = accountService.createAccount(user);
        user.setAccount(account);
        userRepository.save(user);

        return UserMapper.toDTO(user);
    }
}
