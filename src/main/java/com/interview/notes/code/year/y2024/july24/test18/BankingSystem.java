package com.interview.notes.code.year.y2024.july24.test18;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingSystem {
    private Map<String, Double> accounts;

    public BankingSystem() {
        this.accounts = new HashMap<>();
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();

        List<String[]> operations = Arrays.asList(
                new String[]{"CREATE_ACCOUNT", "account1"},
                new String[]{"CREATE_ACCOUNT", "account1"},
                new String[]{"CREATE_ACCOUNT", "account2"},
                new String[]{"DEPOSIT", "non-existing", "2700"},
                new String[]{"DEPOSIT", "account1", "2706"}
        );

        for (String[] operation : operations) {
            switch (operation[0]) {
                case "CREATE_ACCOUNT":
                    System.out.println("CREATE_ACCOUNT " + operation[1] + ": " + bank.createAccount(operation[1]));
                    break;
                case "DEPOSIT":
                    double amount = Double.parseDouble(operation[2]);
                    System.out.println("DEPOSIT " + operation[1] + " " + operation[2] + ": " + bank.deposit(operation[1], amount));
                    break;
                default:
                    System.out.println("Unknown operation: " + operation[0]);
            }
        }
    }

    public boolean createAccount(String accountId) {
        if (accountId == null || accountId.trim().isEmpty()) {
            return false;
        }
        return accounts.putIfAbsent(accountId, 0.0) == null;
    }

    public double deposit(String accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            return -1;
        }
        if (amount < 0) {
            return accounts.get(accountId);  // No change if negative amount
        }
        return accounts.compute(accountId, (k, v) -> v + amount);
    }
}
