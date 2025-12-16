package com.interview.notes.code.year.y2025.december.capitalOne.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BankingSystem {
    
    // Map to store account id and their balance
    // Key = accountId, Value = balance amount
    private final Map<String, Long> accounts;
    
    // Constructor to initialize the banking system
    public BankingSystem() {
        // Create empty HashMap to store accounts
        // Using HashMap for O(1) lookup time
        this.accounts = new HashMap<>();
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Counter for tracking test results
        int passCount = 0;
        int failCount = 0;

        // ============ TEST CASE 1 ============
        // Basic create account test
        System.out.println("=== TEST CASE 1: Basic Create Account ===");
        BankingSystem bank1 = new BankingSystem();
        String[] ops1 = {"CREATE_ACCOUNT acc1", "CREATE_ACCOUNT acc2"};
        List<String> expected1 = List.of("true", "true");
        List<String> result1 = bank1.processOperations(ops1);
        if (result1.equals(expected1)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected1 + ", Got: " + result1);
            failCount++;
        }

        // ============ TEST CASE 2 ============
        // Duplicate account creation test
        System.out.println("\n=== TEST CASE 2: Duplicate Account Creation ===");
        BankingSystem bank2 = new BankingSystem();
        String[] ops2 = {"CREATE_ACCOUNT acc1", "CREATE_ACCOUNT acc1"};
        List<String> expected2 = List.of("true", "false");
        List<String> result2 = bank2.processOperations(ops2);
        if (result2.equals(expected2)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected2 + ", Got: " + result2);
            failCount++;
        }

        // ============ TEST CASE 3 ============
        // Deposit to existing account test
        System.out.println("\n=== TEST CASE 3: Deposit to Existing Account ===");
        BankingSystem bank3 = new BankingSystem();
        String[] ops3 = {"CREATE_ACCOUNT acc1", "DEPOSIT acc1 100"};
        List<String> expected3 = List.of("true", "100");
        List<String> result3 = bank3.processOperations(ops3);
        if (result3.equals(expected3)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected3 + ", Got: " + result3);
            failCount++;
        }

        // ============ TEST CASE 4 ============
        // Deposit to non-existing account test
        System.out.println("\n=== TEST CASE 4: Deposit to Non-Existing Account ===");
        BankingSystem bank4 = new BankingSystem();
        String[] ops4 = {"DEPOSIT acc1 100"};
        List<String> expected4 = List.of("-1");
        List<String> result4 = bank4.processOperations(ops4);
        if (result4.equals(expected4)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected4 + ", Got: " + result4);
            failCount++;
        }

        // ============ TEST CASE 5 ============
        // Multiple deposits test
        System.out.println("\n=== TEST CASE 5: Multiple Deposits ===");
        BankingSystem bank5 = new BankingSystem();
        String[] ops5 = {
            "CREATE_ACCOUNT acc1",
            "DEPOSIT acc1 100",
            "DEPOSIT acc1 200",
            "DEPOSIT acc1 50"
        };
        List<String> expected5 = List.of("true", "100", "300", "350");
        List<String> result5 = bank5.processOperations(ops5);
        if (result5.equals(expected5)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected5 + ", Got: " + result5);
            failCount++;
        }

        // ============ TEST CASE 6 ============
        // Mixed operations test
        System.out.println("\n=== TEST CASE 6: Mixed Operations ===");
        BankingSystem bank6 = new BankingSystem();
        String[] ops6 = {
            "CREATE_ACCOUNT acc1",
            "CREATE_ACCOUNT acc2",
            "DEPOSIT acc1 500",
            "DEPOSIT acc2 300",
            "CREATE_ACCOUNT acc1",
            "DEPOSIT acc3 100"
        };
        List<String> expected6 = List.of("true", "true", "500", "300", "false", "-1");
        List<String> result6 = bank6.processOperations(ops6);
        if (result6.equals(expected6)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected6 + ", Got: " + result6);
            failCount++;
        }

        // ============ TEST CASE 7 ============
        // Large data test - Many accounts
        System.out.println("\n=== TEST CASE 7: Large Data - Many Accounts ===");
        BankingSystem bank7 = new BankingSystem();
        int numAccounts = 100000;
        // Create array of operations for many accounts
        String[] ops7 = IntStream.range(0, numAccounts)
            .mapToObj(i -> "CREATE_ACCOUNT acc" + i)
            .toArray(String[]::new);
        long startTime = System.currentTimeMillis();
        List<String> result7 = bank7.processOperations(ops7);
        long endTime = System.currentTimeMillis();
        // Check all results are "true"
        boolean allTrue = result7.stream().allMatch(r -> r.equals("true"));
        if (allTrue && result7.size() == numAccounts) {
            System.out.println("PASS (Time: " + (endTime - startTime) + "ms)");
            passCount++;
        } else {
            System.out.println("FAIL - Not all accounts created successfully");
            failCount++;
        }

        // ============ TEST CASE 8 ============
        // Large data test - Many deposits
        System.out.println("\n=== TEST CASE 8: Large Data - Many Deposits ===");
        BankingSystem bank8 = new BankingSystem();
        bank8.createAccount("bigAccount");
        int numDeposits = 100000;
        String[] ops8 = IntStream.range(0, numDeposits)
            .mapToObj(i -> "DEPOSIT bigAccount 1")
            .toArray(String[]::new);
        startTime = System.currentTimeMillis();
        List<String> result8 = bank8.processOperations(ops8);
        endTime = System.currentTimeMillis();
        // Final balance should equal number of deposits
        String lastBalance = result8.get(result8.size() - 1);
        if (lastBalance.equals(String.valueOf(numDeposits))) {
            System.out.println("PASS (Time: " + (endTime - startTime) + "ms)");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + numDeposits + ", Got: " + lastBalance);
            failCount++;
        }

        // ============ TEST CASE 9 ============
        // Large amount deposit test
        System.out.println("\n=== TEST CASE 9: Large Amount Deposit ===");
        BankingSystem bank9 = new BankingSystem();
        String[] ops9 = {
            "CREATE_ACCOUNT rich",
            "DEPOSIT rich 9999999999999"
        };
        List<String> expected9 = List.of("true", "9999999999999");
        List<String> result9 = bank9.processOperations(ops9);
        if (result9.equals(expected9)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected9 + ", Got: " + result9);
            failCount++;
        }

        // ============ TEST CASE 10 ============
        // Empty account id edge case
        System.out.println("\n=== TEST CASE 10: Zero Deposit ===");
        BankingSystem bank10 = new BankingSystem();
        String[] ops10 = {
            "CREATE_ACCOUNT zero",
            "DEPOSIT zero 0",
            "DEPOSIT zero 100"
        };
        List<String> expected10 = List.of("true", "0", "100");
        List<String> result10 = bank10.processOperations(ops10);
        if (result10.equals(expected10)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected10 + ", Got: " + result10);
            failCount++;
        }

        // ============ FINAL SUMMARY ============
        System.out.println("\n========================================");
        System.out.println("FINAL TEST RESULTS");
        System.out.println("========================================");
        System.out.println("Total Tests: " + (passCount + failCount));
        System.out.println("Passed: " + passCount);
        System.out.println("Failed: " + failCount);
        if (failCount == 0) {
            System.out.println("STATUS: ALL TESTS PASSED!");
        } else {
            System.out.println("STATUS: SOME TESTS FAILED!");
        }
    }
    
    // Method to create a new account
    // Returns "true" if account created successfully
    // Returns "false" if account already exists
    public String createAccount(String accountId) {
        // Check if account already exists in our map
        // containsKey returns true if key is present
        if (accounts.containsKey(accountId)) {
            // Account exists, so we cannot create it again
            return "false";
        }
        // Account does not exist, so we create it
        // Initial balance is set to 0
        accounts.put(accountId, 0L);
        // Return true as account was created successfully
        return "true";
    }
    
    // Method to deposit money into an account
    // Returns new balance after deposit
    // Returns -1 if account does not exist
    public long deposit(String accountId, long amount) {
        // First check if account exists
        // If not, return -1 as per requirement
        if (!accounts.containsKey(accountId)) {
            // Account not found, return -1
            return -1L;
        }
        // Get current balance of the account
        long currentBalance = accounts.get(accountId);
        // Calculate new balance by adding deposit amount
        long newBalance = currentBalance + amount;
        // Update the account with new balance
        accounts.put(accountId, newBalance);
        // Return the new balance after deposit
        return newBalance;
    }
    
    // Method to process operations from string array
    // Each string contains operation and parameters
    public List<String> processOperations(String[] operations) {
        // List to store results of each operation
        List<String> results = new ArrayList<>();

        // Process each operation one by one
        for (String operation : operations) {
            // Split the operation string by space
            // Example: "CREATE_ACCOUNT acc1" becomes ["CREATE_ACCOUNT", "acc1"]
            String[] parts = operation.split(" ");

            // Get the operation type (first part)
            String operationType = parts[0];

            // Check which operation to perform
            if (operationType.equals("CREATE_ACCOUNT")) {
                // Get account id from second part
                String accountId = parts[1];
                // Call create account method and add result
                results.add(createAccount(accountId));
            } else if (operationType.equals("DEPOSIT")) {
                // Get account id from second part
                String accountId = parts[1];
                // Get amount from third part and convert to long
                long amount = Long.parseLong(parts[2]);
                // Call deposit method and add result as string
                results.add(String.valueOf(deposit(accountId, amount)));
            }
        }
        // Return all results
        return results;
    }
}