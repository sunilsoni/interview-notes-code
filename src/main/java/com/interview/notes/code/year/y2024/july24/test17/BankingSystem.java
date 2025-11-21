package com.interview.notes.code.year.y2024.july24.test17;

import java.util.HashMap;
import java.util.Map;

public class BankingSystem {
    private final Map<String, Double> accounts;

    public BankingSystem() {
        this.accounts = new HashMap<>();
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();

        // Test cases
        System.out.println("Create account 'acc1': " + bank.createAccount("acc1"));
        System.out.println("Create account 'acc1' again: " + bank.createAccount("acc1"));
        System.out.println("Create account 'acc2': " + bank.createAccount("acc2"));

        System.out.println("Deposit 100 to 'acc1': " + bank.deposit("acc1", 100));
        System.out.println("Deposit 50 to 'acc1': " + bank.deposit("acc1", 50));
        System.out.println("Deposit 200 to 'acc2': " + bank.deposit("acc2", 200));
        System.out.println("Deposit 75 to non-existent 'acc3': " + bank.deposit("acc3", 75));

        // Additional test cases
        System.out.println("Create account with empty string: " + bank.createAccount(""));
        System.out.println("Deposit negative amount to 'acc1': " + bank.deposit("acc1", -25));
        System.out.println("Deposit zero amount to 'acc2': " + bank.deposit("acc2", 0));
    }

    public boolean createAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            return false;
        }
        accounts.put(accountId, 0.0);
        return true;
    }

    public double deposit(String accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            return -1;
        }
        double newBalance = accounts.get(accountId) + amount;
        accounts.put(accountId, newBalance);
        return newBalance;
    }
}
