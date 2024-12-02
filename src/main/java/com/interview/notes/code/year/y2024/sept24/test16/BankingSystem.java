package com.interview.notes.code.year.y2024.sept24.test16;

import java.util.HashMap;
import java.util.Scanner;

public class BankingSystem {

    // HashMap to store account balances
    private HashMap<String, Integer> accounts;

    // Constructor to initialize the banking system
    public BankingSystem() {
        accounts = new HashMap<>();
    }

    // Main method to process input and output
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingSystem1 bankingSystem = new BankingSystem1();

        // Process input commands
        while (scanner.hasNext()) {
            String command = scanner.next();

            if (command.equals("CREATE_ACCOUNT")) {
                String accountId = scanner.next();
                boolean result = bankingSystem.createAccount(accountId);
                System.out.println(result);
            } else if (command.equals("DEPOSIT")) {
                String accountId = scanner.next();
                int amount = scanner.nextInt();
                // int result = bankingSystem.deposit(accountId, amount);
                // System.out.println(result);
            }
        }

        scanner.close();
    }

    // Method to create a new account
    public boolean createAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            return false; // Account already exists
        } else {
            accounts.put(accountId, 0); // Create account with initial balance 0
            return true;
        }
    }

    // Method to deposit money into an account
    public int deposit(String accountId, int amount) {
        if (!accounts.containsKey(accountId)) {
            return -1; // Account does not exist
        } else {
            int newBalance = accounts.get(accountId) + amount;
            accounts.put(accountId, newBalance); // Update the account balance
            return newBalance;
        }
    }
}
