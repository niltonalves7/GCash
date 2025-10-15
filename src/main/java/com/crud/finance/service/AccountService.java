package com.crud.finance.service;

import com.crud.finance.exceptions.ResourceNotFoundException;
import com.crud.finance.model.Account;
import com.crud.finance.model.User;
import com.crud.finance.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(User user) {
        Account account = Account.builder()
                .accountNumber(generateAccountNumber())
                .balance(BigDecimal.ZERO)
                .user(user)
                .build();
        return accountRepository.save(account);
    }

    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found."));
    }

    private String generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return "ACC-" + uuid.substring(0, 10).toUpperCase();
    }
}
