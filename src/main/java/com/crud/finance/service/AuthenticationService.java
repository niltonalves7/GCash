package com.crud.finance.service;

import com.crud.finance.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

}
