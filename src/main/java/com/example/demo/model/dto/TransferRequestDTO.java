package com.example.demo.model.dto;

public record TransferRequestDTO (
        Long toAccount,
        Long fromAccount,
        int amount
){}
