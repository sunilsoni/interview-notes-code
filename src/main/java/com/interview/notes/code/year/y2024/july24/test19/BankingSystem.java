package com.interview.notes.code.year.y2024.july24.test19;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingSystem {
    private final Map<String, Double> accounts;
    private final Map<String, Double> activityIndicators;

    public BankingSystem() {
        this.accounts = new HashMap<>();
        this.activityIndicators = new HashMap<>();
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();

        List<String[]> operations = Arrays.asList(
                new String[]{"CREATE_ACCOUNT", "account1"},
                new String[]{"CREATE_ACCOUNT", "account2"},
                new String[]{"CREATE_ACCOUNT", "account3"},
                new String[]{"DEPOSIT", "account1", "1000"},
                new String[]{"DEPOSIT", "account2", "2000"},
                new String[]{"DEPOSIT", "account3", "3000"},
                new String[]{"TRANSFER", "account2", "account1", "500"},
                new String[]{"TRANSFER", "account3", "account1", "1000"},
                new String[]{"TOP_ACTIVITY", "2"},
                new String[]{"TOP_ACTIVITY", "5"}
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
                case "TOP_ACTIVITY":
                    int n = Integer.parseInt(operation[1]);
                    System.out.println("TOP_ACTIVITY " + n + ": " + Arrays.toString(bank.topActivity(n)));
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
        boolean created = accounts.putIfAbsent(accountId, 0.0) == null;
        if (created) {
            activityIndicators.put(accountId, 0.0);
        }
        return created;
    }

    public double deposit(String accountId, double amount) {
        if (!accounts.containsKey(accountId) || amount < 0) {
            return -1;
        }
        accounts.compute(accountId, (k, v) -> v + amount);
        updateActivityIndicator(accountId, amount);
        return accounts.get(accountId);
    }

    public double transfer(String fromId, String toId, double amount) {
        if (!accounts.containsKey(fromId) || !accounts.containsKey(toId) || fromId.equals(toId)) {
            return -1;
        }
        double fromBalance = accounts.get(fromId);
        if (fromBalance < amount) {
            return -1;
        }
        accounts.compute(fromId, (k, v) -> v - amount);
        accounts.compute(toId, (k, v) -> v + amount);
        updateActivityIndicator(fromId, amount);
        updateActivityIndicator(toId, amount);
        return accounts.get(fromId);
    }

    private void updateActivityIndicator(String accountId, double amount) {
        activityIndicators.compute(accountId, (k, v) -> v + Math.abs(amount));
    }

    public String[] topActivity(int n) {
        return activityIndicators.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .map(e -> String.format("%s(%.2f)", e.getKey(), e.getValue()))
                .toArray(String[]::new);
    }
}
