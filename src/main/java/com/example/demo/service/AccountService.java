package com.example.demo.service;

import com.example.demo.exceptions.InsufficientFundsException;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;


    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> getAllAccounts(){
        return repository.findAll();
    }

    @Transactional
    public void transfer(Long fromId, Long toId, int amount) {

        Account from = repository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("From Account not found"));
        from.setBalance(from.getBalance() - amount);



        if(from.getBalance() < 0){
            throw new InsufficientFundsException("Insufficient funds");
        }

        Account to = repository.findById(toId)
                .orElseThrow(() -> new RuntimeException("To Account not found"));

        to.setBalance(to.getBalance() + amount);

        repository.save(from);
        repository.save(to);
    }
}
