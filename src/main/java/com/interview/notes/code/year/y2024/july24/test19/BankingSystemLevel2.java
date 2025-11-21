package com.interview.notes.code.year.y2024.july24.test19;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingSystemLevel2 {
    private final Map<String, Double> accounts;

    public BankingSystemLevel2() {
        this.accounts = new HashMap<>();
    }

    public static void main(String[] args) {
        BankingSystemLevel2 bank = new BankingSystemLevel2();

        List<String[]> operations = Arrays.asList(
                new String[]{"CREATE_ACCOUNT", "account1"},
                new String[]{"CREATE_ACCOUNT", "account1"},
                new String[]{"CREATE_ACCOUNT", "account2"},
                new String[]{"DEPOSIT", "non-existing", "2700"},
                new String[]{"DEPOSIT", "account1", "2706"},
                new String[]{"DEPOSIT", "account2", "5000"},
                new String[]{"TRANSFER", "account1", "account2", "1000"},
                new String[]{"TRANSFER", "account1", "account3", "500"},
                new String[]{"TRANSFER", "account1", "account1", "100"},
                new String[]{"TRANSFER", "account1", "account2", "2000"}
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
                case "TRANSFER":
                    double transferAmount = Double.parseDouble(operation[3]);
                    System.out.println("TRANSFER " + operation[1] + " " + operation[2] + " " + operation[3] + ": " +
                            bank.transfer(operation[1], operation[2], transferAmount));
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

    public double transfer(String fromId, String toId, double amount) {
        if (!accounts.containsKey(fromId) || !accounts.containsKey(toId)) {
            return -1;
        }
        if (fromId.equals(toId)) {
            return -1;
        }
        double fromBalance = accounts.get(fromId);
        if (fromBalance < amount) {
            return -1;
        }
        accounts.compute(fromId, (k, v) -> v - amount);
        accounts.compute(toId, (k, v) -> v + amount);
        return accounts.get(fromId);
    }
}
