package com.interview.notes.code.year.y2024.dec24.test10;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BankAccountAPI1 {
    // Store account transactions in a TreeMap for efficient range queries
    private final Map<Long, Map<Long, Transaction>> accounts = new HashMap<>();

    public static void main(String[] args) {
        BankAccountAPI1 bank = new BankAccountAPI1();
        boolean allTestsPassed = true;

        // Test Case 1: Basic operations
        System.out.println("Test Case 1: Basic Operations");
        allTestsPassed &= testEquals(bank.current(1), 0.0, "Initial balance");

        bank.credit(1, 100, 10);
        bank.credit(1, 200, 30);
        bank.debit(1, 300, 5);
        bank.credit(1, 400, 20);
        bank.credit(1, 500, 10);

        allTestsPassed &= testEquals(bank.current(1), 65.0, "Final balance");
        allTestsPassed &= testEquals(bank.balance_change(1, 150, 450), 45.0, "Balance change 150-450");
        allTestsPassed &= testEquals(bank.balance_change(1, 200, 400), 45.0, "Balance change 200-400");
        allTestsPassed &= testEquals(bank.balance_change(1, 0, 600), 65.0, "Balance change 0-600");

        // Test Case 2: Edge cases
        System.out.println("\nTest Case 2: Edge Cases");
        allTestsPassed &= testEquals(bank.current(999), 0.0, "Non-existent account");
        allTestsPassed &= testEquals(bank.balance_change(1, 500, 100), 0.0, "Invalid time range");

        // Test Case 3: Large data
        System.out.println("\nTest Case 3: Large Data");
        BankAccountAPI1 largeBank = new BankAccountAPI1();
        double expectedSum = 0;
        for (int i = 0; i < 10000; i++) {
            double amount = Math.random() * 100;
            expectedSum += amount;
            largeBank.credit(2, i, amount);
        }
        allTestsPassed &= testEquals(largeBank.current(2), expectedSum, "Large data test");

        System.out.println("\nFinal Result: " + (allTestsPassed ? "ALL TESTS PASSED" : "SOME TESTS FAILED"));
    }

    private static boolean testEquals(double actual, double expected, String testName) {
        boolean passed = Math.abs(actual - expected) < 0.0001;
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + actual + ")");
        return passed;
    }

    public void credit(long accountId, long timestamp, double amount) {
        accounts.computeIfAbsent(accountId, k -> new TreeMap<>())
                .put(timestamp, new Transaction(timestamp, amount));
    }

    public void debit(long accountId, long timestamp, double amount) {
        credit(accountId, timestamp, -amount);
    }

    public double current(long accountId) {
        if (!accounts.containsKey(accountId)) {
            return 0.0;
        }
        return accounts.get(accountId).values().stream()
                .mapToDouble(t -> t.amount)
                .sum();
    }

    public double balance_change(long accountId, long startTime, long endTime) {
        if (!accounts.containsKey(accountId)) {
            return 0.0;
        }

        return accounts.get(accountId).values().stream()
                .filter(t -> t.timestamp >= startTime && t.timestamp <= endTime)
                .mapToDouble(t -> t.amount)
                .sum();
    }

    static class Transaction {
        long timestamp;
        double amount;

        Transaction(long timestamp, double amount) {
            this.timestamp = timestamp;
            this.amount = amount;
        }
    }
}
