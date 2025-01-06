package com.interview.notes.code.year.y2024.dec24.test11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Transaction {
    long timestamp;
    long amount;

    Transaction(long timestamp, long amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }
}

class AccountData {
    long currentBalance = 0;
    List<Transaction> transactions = new ArrayList<>();
    List<Long> prefixSums = new ArrayList<>();
}

public class BankAccountAPI {
    private Map<Integer, AccountData> accounts = new HashMap<>();

    // Simple main method tests (no JUnit)
    public static void main(String[] args) {
        BankAccountAPI api = new BankAccountAPI();

        // Test Case 1 (given example)
        System.out.println("current(1) = " + api.current(1) + " (Expect 0)");
        api.credit(1, 100, 10);
        api.credit(1, 200, 30);
        api.debit(1, 300, 5);
        api.credit(1, 400, 20);
        api.credit(1, 500, 10);
        System.out.println("current(1) = " + api.current(1) + " (Expect 65)");

        System.out.println("balance_change(1,150,450) = " + api.balance_change(1, 150, 450) + " (Expect 45)");
        System.out.println("balance_change(1,200,400) = " + api.balance_change(1, 200, 400) + " (Expect 15)");
        System.out.println("balance_change(1,0,600)   = " + api.balance_change(1, 0, 600) + " (Expect 65)");

        // Additional Tests
        System.out.println("current(2) = " + api.current(2) + " (Expect 0)");
        System.out.println("balance_change(2,0,1000) = " + api.balance_change(2, 0, 1000) + " (Expect 0)");

        api.credit(3, 10, 50);
        System.out.println("current(3) = " + api.current(3) + " (Expect 50)");
        System.out.println("balance_change(3,0,10) = " + api.balance_change(3, 0, 10) + " (Expect 50)");
        System.out.println("balance_change(3,10,10) = " + api.balance_change(3, 10, 10) + " (Expect 50)");
        System.out.println("balance_change(3,11,20) = " + api.balance_change(3, 11, 20) + " (Expect 0)");

        api.credit(4, 100, 20);
        api.credit(4, 200, 10);
        System.out.println("balance_change(4,100,200) = " + api.balance_change(4, 100, 200) + " (Expect 30)");

        // Indicate pass/fail based on expectations:
        // (In a real scenario, we would store expected results and compare)
        // Here we just printed out the results and expected values for demonstration.
    }

    public long current(int accountId) {
        AccountData data = accounts.get(accountId);
        if (data == null) return 0;
        return data.currentBalance;
    }

    public void credit(int accountId, long timestamp, long amount) {
        AccountData data = accounts.computeIfAbsent(accountId, k -> new AccountData());
        data.currentBalance += amount;
        data.transactions.add(new Transaction(timestamp, amount));
        long prevSum = data.prefixSums.isEmpty() ? 0 : data.prefixSums.get(data.prefixSums.size() - 1);
        data.prefixSums.add(prevSum + amount);
    }

    public void debit(int accountId, long timestamp, long amount) {
        AccountData data = accounts.computeIfAbsent(accountId, k -> new AccountData());
        data.currentBalance -= amount;
        data.transactions.add(new Transaction(timestamp, -amount));
        long prevSum = data.prefixSums.isEmpty() ? 0 : data.prefixSums.get(data.prefixSums.size() - 1);
        data.prefixSums.add(prevSum - amount);
    }

    public long balance_change(int accountId, long startTime, long endTime) {
        AccountData data = accounts.get(accountId);
        if (data == null || data.transactions.isEmpty()) return 0;

        int startIndex = firstNotBefore(data.transactions, startTime);
        int endIndex = lastNotAfter(data.transactions, endTime);

        if (startIndex == -1 || endIndex == -1 || startIndex > endIndex) return 0;

        long endSum = data.prefixSums.get(endIndex);
        long startSum = startIndex > 0 ? data.prefixSums.get(startIndex - 1) : 0;
        return endSum - startSum;
    }

    // Binary search: find first index where timestamp >= startTime
    private int firstNotBefore(List<Transaction> txns, long startTime) {
        int low = 0, high = txns.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (txns.get(mid).timestamp >= startTime) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Binary search: find last index where timestamp <= endTime
    private int lastNotAfter(List<Transaction> txns, long endTime) {
        int low = 0, high = txns.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (txns.get(mid).timestamp <= endTime) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
