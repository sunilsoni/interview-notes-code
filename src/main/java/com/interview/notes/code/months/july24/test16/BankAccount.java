package com.interview.notes.code.months.july24.test16;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {
    private static Map<AccountDetails, String> accounts = new HashMap<>();

    // Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        AccountDetails accountDetails = new AccountDetails(accountNumber, initialBalance);
        accounts.put(accountDetails, accountNumber);
    }

    // Method to find account details by AccountDetails object
    public static AccountDetails findAccountDetails(AccountDetails accountDetails) {
        return accounts.keySet().stream()
                .filter(details -> details.equals(accountDetails))
                .findFirst()
                .orElse(null);
    }

    // Main method to test BankAccount and AccountDetails
    public static void main(String[] args) {
        // Create accounts
        BankAccount account1 = new BankAccount("12345", 1000);
        BankAccount account2 = new BankAccount("67890", 500);

        // Create AccountDetails instances for searching
        AccountDetails searchDetails = new AccountDetails("12345", 0); // Balance is irrelevant for searching

        // Find and display account details
        AccountDetails foundAccount = BankAccount.findAccountDetails(searchDetails);
        if (foundAccount != null) {
            foundAccount.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }

        // Deposit and withdraw money
        account1.deposit(foundAccount, 200);
        account1.withdraw(foundAccount, 150);

        // Display updated details
        AccountDetails updatedAccount = BankAccount.findAccountDetails(searchDetails);
        if (updatedAccount != null) {
            updatedAccount.displayAccountDetails();
        }
    }

    // Method to deposit money
    public void deposit(AccountDetails accountDetails, double amount) {
        if (accounts.containsKey(accountDetails) && amount > 0) {
            accountDetails.setBalance(accountDetails.getBalance() + amount);
        }
    }

    // Method to withdraw money
    public void withdraw(AccountDetails accountDetails, double amount) {
        if (accounts.containsKey(accountDetails) && amount > 0 && amount <= accountDetails.getBalance()) {
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

        // Override equals and hashCode to ensure correct behavior in the Map
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            AccountDetails that = (AccountDetails) obj;
            return accountNumber.equals(that.accountNumber);
        }

        @Override
        public int hashCode() {
            return accountNumber.hashCode();
        }
    }
}
