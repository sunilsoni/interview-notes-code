package com.interview.notes.code.year.y2026.jan.common.test4;

import com.example.banking.dto.TransactionRequest;
import com.example.banking.service.BankServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    private final BankServiceImpl bankService; // Injected via Constructor

    public BankController(BankServiceImpl bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody TransactionRequest request) {
        bankService.deposit(request.accountId(), request.amount());
        return ResponseEntity.ok("Deposit successful. New Balance: " + 
            bankService.getBalance(request.accountId()));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody TransactionRequest request) {
        bankService.withdraw(request.accountId(), request.amount());
        return ResponseEntity.ok("Withdrawal successful. Remaining Balance: " + 
            bankService.getBalance(request.accountId()));
    }
}