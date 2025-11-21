package com.interview.notes.code.year.y2024.july24.test16;

import java.util.HashMap;
import java.util.Map;

public class BankAccountOld {
    private static final Map<String, AccountDetails> accounts = new HashMap<>();

    // Constructor
    public BankAccountOld(String accountNumber, double initialBalance) {
        AccountDetails accountDetails = new AccountDetails(accountNumber, initialBalance);
        accounts.put(accountNumber, accountDetails);
    }

    // Static method to find account details by account number
    public static AccountDetails findAccountDetails(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // Main method to test BankAccount and AccountDetails
    public static void main(String[] args) {
        // Create accounts
        BankAccountOld account1 = new BankAccountOld("12345", 1000);
        BankAccountOld account2 = new BankAccountOld("67890", 500);

        // Find and display account details
        AccountDetails foundAccount = BankAccountOld.findAccountDetails("12345");
        if (foundAccount != null) {
            foundAccount.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }

        // Deposit and withdraw money
        account1.deposit("12345", 200);
        account1.withdraw("12345", 150);

        // Display updated details
        AccountDetails updatedAccount = BankAccountOld.findAccountDetails("12345");
        if (updatedAccount != null) {
            updatedAccount.displayAccountDetails();
        }
    }

    // Method to deposit money
    public void deposit(String accountNumber, double amount) {
        AccountDetails accountDetails = accounts.get(accountNumber);
        if (accountDetails != null && amount > 0) {
            accountDetails.setBalance(accountDetails.getBalance() + amount);
        }
    }

    // Method to withdraw money
    public void withdraw(String accountNumber, double amount) {
        AccountDetails accountDetails = accounts.get(accountNumber);
        if (accountDetails != null && amount > 0 && amount <= accountDetails.getBalance()) {
            accountDetails.setBalance(accountDetails.getBalance() - amount);
        }
    }

    // Inner class to encapsulate account details
    public static class AccountDetails {
        private String accountNumber;
        private double balance;

        // Constructor
        public AccountDetails(String accountNumber, double initialBalance) {
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
        }

        // Getters and Setters
        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        // Method to display account details
        public void displayAccountDetails() {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Balance: " + balance);
        }
    }
}
