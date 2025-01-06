package com.interview.notes.code.year.y2024.dec24.test11;

import java.util.*;

public class BankAccountAPI1 {

    public static void main(String[] args) {
        // Create a list to hold test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Provided Example Usage
        testCases.add(new TestCase("Example Usage Test", () -> {
            Bank bank = new Bank();
            boolean passed = true;
            if (bank.current(1) != 0) {
                System.out.println("Test Failed: Expected current(1) to return 0.");
                passed = false;
            }
            bank.credit(1, 100, 10);
            bank.credit(1, 200, 30);
            bank.debit(1, 300, 5);
            bank.credit(1, 400, 20);
            bank.credit(1, 500, 10);
            if (bank.current(1) != 65) {
                System.out.println("Test Failed: Expected current(1) to return 65.");
                passed = false;
            }
            if (bank.balanceChange(1, 150, 450) != 45) {
                System.out.println("Test Failed: Expected balance_change(1, 150, 450) to return 45.");
                passed = false;
            }
            if (bank.balanceChange(1, 200, 400) != 45) {
                System.out.println("Test Failed: Expected balance_change(1, 200, 400) to return 45.");
                passed = false;
            }
            if (bank.balanceChange(1, 0, 600) != 65) {
                System.out.println("Test Failed: Expected balance_change(1, 0, 600) to return 65.");
                passed = false;
            }
            if (passed) {
                System.out.println("Test Case 1: PASS");
            } else {
                System.out.println("Test Case 1: FAIL");
            }
        }));

        // Test Case 2: Edge Case with No Transactions
        testCases.add(new TestCase("Edge Case: No Transactions", () -> {
            Bank bank = new Bank();
            boolean passed = true;
            if (bank.current(2) != 0) {
                System.out.println("Test Failed: Expected current(2) to return 0.");
                passed = false;
            }
            if (bank.balanceChange(2, 0, 1000) != 0) {
                System.out.println("Test Failed: Expected balance_change(2, 0, 1000) to return 0.");
                passed = false;
            }
            if (passed) {
                System.out.println("Test Case 2: PASS");
            } else {
                System.out.println("Test Case 2: FAIL");
            }
        }));

        // Test Case 3: Multiple Accounts
        testCases.add(new TestCase("Multiple Accounts Test", () -> {
            Bank bank = new Bank();
            boolean passed = true;
            bank.credit(1, 100, 50);
            bank.debit(1, 200, 20);
            bank.credit(2, 150, 30);
            bank.debit(2, 250, 10);
            if (bank.current(1) != 30) {
                System.out.println("Test Failed: Expected current(1) to return 30.");
                passed = false;
            }
            if (bank.current(2) != 20) {
                System.out.println("Test Failed: Expected current(2) to return 20.");
                passed = false;
            }
            if (bank.balanceChange(1, 0, 300) != 30) {
                System.out.println("Test Failed: Expected balance_change(1, 0, 300) to return 30.");
                passed = false;
            }
            if (bank.balanceChange(2, 100, 200) != 30) {
                System.out.println("Test Failed: Expected balance_change(2, 100, 200) to return 30.");
                passed = false;
            }
            if (passed) {
                System.out.println("Test Case 3: PASS");
            } else {
                System.out.println("Test Case 3: FAIL");
            }
        }));

        // Test Case 4: Transactions at Same Timestamp
        testCases.add(new TestCase("Transactions at Same Timestamp Test", () -> {
            Bank bank = new Bank();
            boolean passed = true;
            bank.credit(1, 100, 100);
            bank.debit(1, 100, 50);
            if (bank.current(1) != 50) {
                System.out.println("Test Failed: Expected current(1) to return 50.");
                passed = false;
            }
            if (bank.balanceChange(1, 100, 100) != 50) {
                System.out.println("Test Failed: Expected balance_change(1, 100, 100) to return 50.");
                passed = false;
            }
            if (passed) {
                System.out.println("Test Case 4: PASS");
            } else {
                System.out.println("Test Case 4: FAIL");
            }
        }));

        // Test Case 5: Large Number of Transactions
        testCases.add(new TestCase("Large Number of Transactions Test", () -> {
            Bank bank = new Bank();
            boolean passed = true;
            int accountId = 1;
            int numTransactions = 100000;
            for (int i = 1; i <= numTransactions; i++) {
                bank.credit(accountId, i, 1);
            }
            if (bank.current(accountId) != numTransactions) {
                System.out.println("Test Failed: Expected current(1) to return " + numTransactions + ".");
                passed = false;
            }
            long balanceChange = bank.balanceChange(accountId, 50000, 100000);
            if (balanceChange != 50001) {
                System.out.println("Test Failed: Expected balance_change(1, 50000, 100000) to return 50001.");
                passed = false;
            }
            if (passed) {
                System.out.println("Test Case 5: PASS");
            } else {
                System.out.println("Test Case 5: FAIL");
            }
        }));

        // Run all test cases
        for (TestCase testCase : testCases) {
            System.out.println("Running: " + testCase.description);
            testCase.testMethod.run();
            System.out.println();
        }
    }

    /**
     * Class representing a bank account with transaction support.
     */
    static class BankAccount {
        private int accountId;
        private long currentBalance;
        // Using TreeMap to store transactions sorted by timestamp for efficient range queries
        private TreeMap<Long, Long> transactions; // key: timestamp, value: net change at that timestamp

        public BankAccount(int accountId) {
            this.accountId = accountId;
            this.currentBalance = 0;
            this.transactions = new TreeMap<>();
        }

        /**
         * Credits the account with the specified amount at the given timestamp.
         */
        public void credit(long timestamp, long amount) {
            currentBalance += amount;
            transactions.put(timestamp, transactions.getOrDefault(timestamp, 0L) + amount);
        }

        /**
         * Debits the account with the specified amount at the given timestamp.
         */
        public void debit(long timestamp, long amount) {
            currentBalance -= amount;
            transactions.put(timestamp, transactions.getOrDefault(timestamp, 0L) - amount);
        }

        /**
         * Returns the current balance of the account.
         */
        public long current() {
            return currentBalance;
        }

        /**
         * Returns the net change in account value between the given timestamps, inclusive.
         * Time complexity: O(log M), where M is the number of transactions.
         */
        public long balanceChange(long startTimestamp, long endTimestamp) {
            if (transactions.isEmpty()) {
                return 0;
            }
            // Get a subMap of transactions within the timestamp range
            NavigableMap<Long, Long> subMap = transactions.subMap(startTimestamp, true, endTimestamp, true);
            long netChange = 0;
            for (long change : subMap.values()) {
                netChange += change;
            }
            return netChange;
        }
    }

    /**
     * Class to handle multiple bank accounts.
     */
    static class Bank {
        private Map<Integer, BankAccount> accounts;

        public Bank() {
            accounts = new HashMap<>();
        }

        /**
         * Retrieves the BankAccount object for the given accountId,
         * creating it if it doesn't exist.
         */
        private BankAccount getAccount(int accountId) {
            return accounts.computeIfAbsent(accountId, BankAccount::new);
        }

        public void credit(int accountId, long timestamp, long amount) {
            getAccount(accountId).credit(timestamp, amount);
        }

        public void debit(int accountId, long timestamp, long amount) {
            getAccount(accountId).debit(timestamp, amount);
        }

        public long current(int accountId) {
            return getAccount(accountId).current();
        }

        public long balanceChange(int accountId, long startTimestamp, long endTimestamp) {
            return getAccount(accountId).balanceChange(startTimestamp, endTimestamp);
        }
    }

    // Helper class to represent a test case
    static class TestCase {
        String description;
        Runnable testMethod;

        TestCase(String description, Runnable testMethod) {
            this.description = description;
            this.testMethod = testMethod;
        }
    }
}