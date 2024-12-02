package com.interview.notes.code.year.y2024.jan24.bank;

// Factory pattern for account creation
public class AccountFactory {
    public Account getAccount(String accountNumber) {
        // This would normally look up an account in a database
        // For this example, we just create a new one
        return new Account(accountNumber, 1234, 1000.0);
    }
}