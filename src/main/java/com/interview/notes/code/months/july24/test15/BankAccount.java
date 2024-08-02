package com.interview.notes.code.months.july24.test15;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        // Add the new account to the static map
        accounts.put(accountNumber, this);
    }

    // Static method to find account details by account number
    public static BankAccount findAccountDetails(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // Main class to test BankAccount
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("12345", 1000);
        BankAccount account2 = new BankAccount("67890", 500);

        // Find and display account details
        BankAccount foundAccount = BankAccount.findAccountDetails("12345");
        if (foundAccount != null) {
            foundAccount.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}
