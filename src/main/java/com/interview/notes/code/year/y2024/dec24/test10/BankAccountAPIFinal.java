package com.interview.notes.code.year.y2024.dec24.test10;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BankAccountAPIFinal {
    private final Map<Long, TreeMap<Long, Double>> accounts = new HashMap<>();

    public static void main(String[] args) {
        BankAccountAPIFinal bank = new BankAccountAPIFinal();

        System.out.println(bank.current(1));  // Expect 0
        bank.credit(1, 100, 10);
        bank.credit(1, 200, 30);  // Balance: 40
        bank.debit(1, 300, 5);    // Balance: 35
        bank.credit(1, 400, 20);  // Balance: 55
        bank.credit(1, 500, 10);  // Balance: 65

        System.out.println(bank.current(1));  // Expect 65
        System.out.println(bank.balance_change(1, 150, 450));  // Expect 45
        System.out.println(bank.balance_change(1, 200, 400));  // Expect 15
        System.out.println(bank.balance_change(1, 0, 600));    // Expect 65

        // Additional test cases for verification
        System.out.println("\nAdditional Test Cases:");
        System.out.println("Balance change (100, 200): " + bank.balance_change(1, 100, 200));  // Should be 40
        System.out.println("Balance change (200, 300): " + bank.balance_change(1, 200, 300));  // Should be -5
        System.out.println("Balance change (300, 400): " + bank.balance_change(1, 300, 400));  // Should be 20
    }

    public void credit(long accountId, long timestamp, double amount) {
        accounts.computeIfAbsent(accountId, k -> new TreeMap<>())
                .put(timestamp, amount);
    }

    public void debit(long accountId, long timestamp, double amount) {
        credit(accountId, timestamp, -amount);
    }

    public double current(long accountId) {
        if (!accounts.containsKey(accountId)) {
            return 0.0;
        }
        return accounts.get(accountId).values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double balance_change(long accountId, long startTime, long endTime) {
        if (!accounts.containsKey(accountId)) {
            return 0.0;
        }

        TreeMap<Long, Double> transactions = accounts.get(accountId);

        // Calculate balance at endTime
        double endBalance = transactions.entrySet().stream()
                .filter(e -> e.getKey() <= endTime)
                .mapToDouble(Map.Entry::getValue)
                .sum();

        // Calculate balance just before startTime
        double startBalance = transactions.entrySet().stream()
                .filter(e -> e.getKey() < startTime)
                .mapToDouble(Map.Entry::getValue)
                .sum();

        return endBalance - startBalance;
    }
}
