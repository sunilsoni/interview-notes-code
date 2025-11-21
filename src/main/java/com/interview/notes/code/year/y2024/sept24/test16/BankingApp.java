package com.interview.notes.code.year.y2024.sept24.test16;

import java.util.HashMap;
import java.util.Map;

/*
Your task is to implement a simplified version of a banking system.
Initially, the banking system does not contain any accounts, so implement operations to allow accounts creation as well as making deposits.
Operations:
CREATE_ACCOUNT <accountId>
Should create a new account with the given identifier if it doesn't already exist.
Returns "true" if an account was successfully created,
Returns "false" if an account with accountId already exists.
DEPOSIT <accountId> <amount>
Should deposit the given amount of money to the specified account accountId
Returns the total amount of money in the account after the query has been processed.
If the specified account doesn't exist, it should return -1.

These inputs are provided as an example of how the input data can be structured, you may select any of these formats.

Array
operations = [
  ["CREATE_ACCOUNT", "account1"],
  ["CREATE_ACCOUNT", "account1"],
  ["CREATE_ACCOUNT", "account2"],
  ["DEPOSIT", "non-existing", "2700"],
  ["DEPOSIT", "account1", "2700"]
]
 */
// Account class representing each individual bank account
class Account {
    private final String accountId;
    private double balance;

    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}

// BankingSystem class managing all accounts and operations
class BankingSystem1 {
    private final Map<String, Account> accounts;

    public BankingSystem1() {
        this.accounts = new HashMap<>();
    }

    // Create a new account if it does not exist
    public boolean createAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            return false; // Account already exists
        }
        accounts.put(accountId, new Account(accountId));
        return true;
    }

    // Deposit money into an account if it exists
    public double deposit(String accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            return -1; // Account doesn't exist
        }
        Account account = accounts.get(accountId);
        account.deposit(amount);
        return account.getBalance();
    }
}

// Main class to simulate the system
public class BankingApp {
    public static void main(String[] args) {
        // Sample operations
        String[][] operations = {
                {"CREATE_ACCOUNT", "account1"},
                {"CREATE_ACCOUNT", "account1"},
                {"CREATE_ACCOUNT", "account2"},
                {"DEPOSIT", "non-existing", "2700"},
                {"DEPOSIT", "account1", "2700"}
        };

        // Initialize the banking system
        BankingSystem1 bankingSystem = new BankingSystem1();

        // Process each operation
        for (String[] operation : operations) {
            String command = operation[0];
            switch (command) {
                case "CREATE_ACCOUNT":
                    String accountId = operation[1];
                    boolean accountCreated = bankingSystem.createAccount(accountId);
                    System.out.println(accountCreated);
                    break;

                case "DEPOSIT":
                    String depositAccountId = operation[1];
                    double amount = Double.parseDouble(operation[2]);
                    double newBalance = bankingSystem.deposit(depositAccountId, amount);
                    System.out.println(newBalance);
                    break;

                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
