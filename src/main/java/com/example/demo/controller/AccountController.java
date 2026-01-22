package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.dto.TransferRequestDTO;
import com.example.demo.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {


    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccount(){
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @PutMapping
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDTO request){
        service.transfer(request.fromAccount(), request.toAccount(), request.amount());
        return ResponseEntity.ok("Transfer successful");
    }
}
