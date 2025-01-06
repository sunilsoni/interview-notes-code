package com.interview.notes.code.year.y2024.dec24.test10;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BankAccountAPI {
    // Store cumulative balances instead of individual transactions
    private Map<Long, TreeMap<Long, Double>> accounts = new HashMap<>();

    public static void main(String[] args) {
        BankAccountAPI bank = new BankAccountAPI();

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
    }

    public void credit(long accountId, long timestamp, double amount) {
        TreeMap<Long, Double> account = accounts.computeIfAbsent(accountId, k -> new TreeMap<>());

        // Get the previous cumulative balance
        Map.Entry<Long, Double> prevEntry = account.floorEntry(timestamp);
        double prevBalance = (prevEntry != null) ? prevEntry.getValue() : 0.0;

        // Update all future cumulative balances
        double newBalance = prevBalance + amount;
        account.put(timestamp, newBalance);

        // Update all subsequent entries
        for (Map.Entry<Long, Double> entry : account.tailMap(timestamp, false).entrySet()) {
            entry.setValue(entry.getValue() + amount);
        }
    }

    public void debit(long accountId, long timestamp, double amount) {
        credit(accountId, timestamp, -amount);
    }

    public double current(long accountId) {
        TreeMap<Long, Double> account = accounts.get(accountId);
        if (account == null || account.isEmpty()) {
            return 0.0;
        }
        return account.lastEntry().getValue();
    }

    public double balance_change(long accountId, long startTime, long endTime) {
        TreeMap<Long, Double> account = accounts.get(accountId);
        if (account == null || account.isEmpty()) {
            return 0.0;
        }

        // Get balance at endTime
        Map.Entry<Long, Double> endEntry = account.floorEntry(endTime);
        double endBalance = endEntry != null ? endEntry.getValue() : 0.0;

        // Get balance just before startTime
        Map.Entry<Long, Double> startEntry = account.lowerEntry(startTime);
        double startBalance = startEntry != null ? startEntry.getValue() : 0.0;

        return endBalance - startBalance;
    }
}
