package com.interview.notes.code.year.y2025.december.capitalOne.test2;

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
        // Basic successful transfer test
        System.out.println("\n=== TEST CASE 5: Basic Successful Transfer ===");
        BankingSystem bank5 = new BankingSystem();
        String[] ops5 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 500",
                "TRANSFER acc1 acc2 200"
        };
        // After transfer: acc1 has 500-200=300, acc2 has 200
        List<String> expected5 = List.of("true", "true", "500", "300");
        List<String> result5 = bank5.processOperations(ops5);
        if (result5.equals(expected5)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected5 + ", Got: " + result5);
            failCount++;
        }

        // ============ TEST CASE 6 ============
        // Transfer from non-existing account test
        System.out.println("\n=== TEST CASE 6: Transfer from Non-Existing Account ===");
        BankingSystem bank6 = new BankingSystem();
        String[] ops6 = {
                "CREATE_ACCOUNT acc2",
                "TRANSFER acc1 acc2 100"
        };
        List<String> expected6 = List.of("true", "-1");
        List<String> result6 = bank6.processOperations(ops6);
        if (result6.equals(expected6)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected6 + ", Got: " + result6);
            failCount++;
        }

        // ============ TEST CASE 7 ============
        // Transfer to non-existing account test
        System.out.println("\n=== TEST CASE 7: Transfer to Non-Existing Account ===");
        BankingSystem bank7 = new BankingSystem();
        String[] ops7 = {
                "CREATE_ACCOUNT acc1",
                "DEPOSIT acc1 500",
                "TRANSFER acc1 acc2 100"
        };
        List<String> expected7 = List.of("true", "500", "-1");
        List<String> result7 = bank7.processOperations(ops7);
        if (result7.equals(expected7)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected7 + ", Got: " + result7);
            failCount++;
        }

        // ============ TEST CASE 8 ============
        // Transfer to same account test
        System.out.println("\n=== TEST CASE 8: Transfer to Same Account ===");
        BankingSystem bank8 = new BankingSystem();
        String[] ops8 = {
                "CREATE_ACCOUNT acc1",
                "DEPOSIT acc1 500",
                "TRANSFER acc1 acc1 100"
        };
        List<String> expected8 = List.of("true", "500", "-1");
        List<String> result8 = bank8.processOperations(ops8);
        if (result8.equals(expected8)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected8 + ", Got: " + result8);
            failCount++;
        }

        // ============ TEST CASE 9 ============
        // Transfer with insufficient funds test
        System.out.println("\n=== TEST CASE 9: Transfer with Insufficient Funds ===");
        BankingSystem bank9 = new BankingSystem();
        String[] ops9 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 100",
                "TRANSFER acc1 acc2 200"
        };
        List<String> expected9 = List.of("true", "true", "100", "-1");
        List<String> result9 = bank9.processOperations(ops9);
        if (result9.equals(expected9)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected9 + ", Got: " + result9);
            failCount++;
        }

        // ============ TEST CASE 10 ============
        // Multiple transfers test
        System.out.println("\n=== TEST CASE 10: Multiple Transfers ===");
        BankingSystem bank10 = new BankingSystem();
        String[] ops10 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "CREATE_ACCOUNT acc3",
                "DEPOSIT acc1 1000",
                "TRANSFER acc1 acc2 300",
                "TRANSFER acc1 acc3 200",
                "TRANSFER acc2 acc3 100"
        };
        // acc1: 1000 -> 700 -> 500
        // acc2: 0 -> 300 -> 200
        // acc3: 0 -> 200 -> 300
        List<String> expected10 = List.of("true", "true", "true", "1000", "700", "500", "200");
        List<String> result10 = bank10.processOperations(ops10);
        if (result10.equals(expected10)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected10 + ", Got: " + result10);
            failCount++;
        }

        // ============ TEST CASE 11 ============
        // Transfer exact balance test
        System.out.println("\n=== TEST CASE 11: Transfer Exact Balance ===");
        BankingSystem bank11 = new BankingSystem();
        String[] ops11 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 500",
                "TRANSFER acc1 acc2 500"
        };
        // acc1 should have 0 after transfer
        List<String> expected11 = List.of("true", "true", "500", "0");
        List<String> result11 = bank11.processOperations(ops11);
        if (result11.equals(expected11)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected11 + ", Got: " + result11);
            failCount++;
        }

        // ============ TEST CASE 12 ============
        // Transfer zero amount test
        System.out.println("\n=== TEST CASE 12: Transfer Zero Amount ===");
        BankingSystem bank12 = new BankingSystem();
        String[] ops12 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 500",
                "TRANSFER acc1 acc2 0"
        };
        // Zero transfer should work, balance remains 500
        List<String> expected12 = List.of("true", "true", "500", "500");
        List<String> result12 = bank12.processOperations(ops12);
        if (result12.equals(expected12)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected12 + ", Got: " + result12);
            failCount++;
        }

        // ============ TEST CASE 13 ============
        // Mixed operations test
        System.out.println("\n=== TEST CASE 13: Mixed Operations ===");
        BankingSystem bank13 = new BankingSystem();
        String[] ops13 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 1000",
                "DEPOSIT acc2 500",
                "TRANSFER acc1 acc2 300",
                "CREATE_ACCOUNT acc1",
                "DEPOSIT acc1 200",
                "TRANSFER acc2 acc1 400"
        };
        // acc1: 1000 -> 700 -> 900 -> 1300
        // acc2: 500 -> 800 -> 400
        List<String> expected13 = List.of("true", "true", "1000", "500", "700", "false", "900", "400");
        List<String> result13 = bank13.processOperations(ops13);
        if (result13.equals(expected13)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected13 + ", Got: " + result13);
            failCount++;
        }

        // ============ TEST CASE 14 ============
        // Large data test - Many accounts and transfers
        System.out.println("\n=== TEST CASE 14: Large Data - Many Accounts ===");
        BankingSystem bank14 = new BankingSystem();
        int numAccounts = 50000;
        // Create many accounts
        String[] createOps = IntStream.range(0, numAccounts)
                .mapToObj(i -> "CREATE_ACCOUNT acc" + i)
                .toArray(String[]::new);
        long startTime = System.currentTimeMillis();
        List<String> createResults = bank14.processOperations(createOps);
        long endTime = System.currentTimeMillis();
        // Check all accounts created successfully
        boolean allCreated = createResults.stream().allMatch(r -> r.equals("true"));
        if (allCreated && createResults.size() == numAccounts) {
            System.out.println("PASS (Time: " + (endTime - startTime) + "ms)");
            passCount++;
        } else {
            System.out.println("FAIL - Not all accounts created");
            failCount++;
        }

        // ============ TEST CASE 15 ============
        // Large data test - Many transfers
        System.out.println("\n=== TEST CASE 15: Large Data - Many Transfers ===");
        BankingSystem bank15 = new BankingSystem();
        // Create two accounts
        bank15.createAccount("sender");
        bank15.createAccount("receiver");
        // Deposit large amount to sender
        bank15.deposit("sender", 1000000L);
        // Create many transfer operations
        int numTransfers = 10000;
        String[] transferOps = IntStream.range(0, numTransfers)
                .mapToObj(i -> "TRANSFER sender receiver 1")
                .toArray(String[]::new);
        startTime = System.currentTimeMillis();
        List<String> transferResults = bank15.processOperations(transferOps);
        endTime = System.currentTimeMillis();
        // Final balance of sender should be 1000000 - 10000 = 990000
        String lastBalance = transferResults.get(transferResults.size() - 1);
        long expectedBalance = 1000000L - numTransfers;
        if (lastBalance.equals(String.valueOf(expectedBalance))) {
            System.out.println("PASS (Time: " + (endTime - startTime) + "ms)");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expectedBalance + ", Got: " + lastBalance);
            failCount++;
        }

        // ============ TEST CASE 16 ============
        // Large amount transfer test
        System.out.println("\n=== TEST CASE 16: Large Amount Transfer ===");
        BankingSystem bank16 = new BankingSystem();
        String[] ops16 = {
                "CREATE_ACCOUNT rich",
                "CREATE_ACCOUNT lucky",
                "DEPOSIT rich 9999999999999",
                "TRANSFER rich lucky 5000000000000"
        };
        // rich: 9999999999999 - 5000000000000 = 4999999999999
        List<String> expected16 = List.of("true", "true", "9999999999999", "4999999999999");
        List<String> result16 = bank16.processOperations(ops16);
        if (result16.equals(expected16)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected16 + ", Got: " + result16);
            failCount++;
        }

        // ============ TEST CASE 17 ============
        // Chain transfer test
        System.out.println("\n=== TEST CASE 17: Chain Transfer ===");
        BankingSystem bank17 = new BankingSystem();
        String[] ops17 = {
                "CREATE_ACCOUNT a",
                "CREATE_ACCOUNT b",
                "CREATE_ACCOUNT c",
                "CREATE_ACCOUNT d",
                "DEPOSIT a 1000",
                "TRANSFER a b 1000",
                "TRANSFER b c 1000",
                "TRANSFER c d 1000"
        };
        // a:1000->0, b:0->1000->0, c:0->1000->0, d:0->1000
        List<String> expected17 = List.of("true", "true", "true", "true", "1000", "0", "0", "0");
        List<String> result17 = bank17.processOperations(ops17);
        if (result17.equals(expected17)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected17 + ", Got: " + result17);
            failCount++;
        }

        // ============ TEST CASE 18 ============
        // Both accounts don't exist test
        System.out.println("\n=== TEST CASE 18: Both Accounts Don't Exist ===");
        BankingSystem bank18 = new BankingSystem();
        String[] ops18 = {
                "TRANSFER acc1 acc2 100"
        };
        List<String> expected18 = List.of("-1");
        List<String> result18 = bank18.processOperations(ops18);
        if (result18.equals(expected18)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected18 + ", Got: " + result18);
            failCount++;
        }

        // ============ TEST CASE 19 ============
        // Transfer after failed transfer test
        System.out.println("\n=== TEST CASE 19: Transfer After Failed Transfer ===");
        BankingSystem bank19 = new BankingSystem();
        String[] ops19 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 100",
                "TRANSFER acc1 acc2 200",
                "TRANSFER acc1 acc2 50"
        };
        // First transfer fails (insufficient), second should succeed
        // acc1: 100 -> 100 -> 50
        List<String> expected19 = List.of("true", "true", "100", "-1", "50");
        List<String> result19 = bank19.processOperations(ops19);
        if (result19.equals(expected19)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected19 + ", Got: " + result19);
            failCount++;
        }

        // ============ TEST CASE 20 ============
        // Stress test - rapid operations
        System.out.println("\n=== TEST CASE 20: Stress Test - Rapid Operations ===");
        BankingSystem bank20 = new BankingSystem();
        // Create 1000 accounts with deposits and transfers
        List<String> stressOps = new ArrayList<>();
        int stressCount = 1000;
        // First create all accounts
        for (int i = 0; i < stressCount; i++) {
            stressOps.add("CREATE_ACCOUNT stress" + i);
        }
        // Deposit to first account
        stressOps.add("DEPOSIT stress0 " + (stressCount * 10));
        // Transfer through all accounts
        for (int i = 0; i < stressCount - 1; i++) {
            stressOps.add("TRANSFER stress" + i + " stress" + (i + 1) + " 10");
        }
        startTime = System.currentTimeMillis();
        List<String> stressResults = bank20.processOperations(stressOps.toArray(new String[0]));
        endTime = System.currentTimeMillis();
        // Verify no errors in results (no unexpected -1)
        // Count how many results are "-1" (should be 0 for valid operations)
        boolean noErrors = true;
        // Skip create results, check deposit and transfers
        for (int i = stressCount + 1; i < stressResults.size(); i++) {
            if (stressResults.get(i).equals("-1")) {
                noErrors = false;
                break;
            }
        }
        if (noErrors) {
            System.out.println("PASS (Time: " + (endTime - startTime) + "ms)");
            passCount++;
        } else {
            System.out.println("FAIL - Unexpected errors in stress test");
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

    // Method to transfer money between accounts
    // Returns balance of fromId after successful transfer
    // Returns -1 if transfer fails for any reason
    public long transfer(String fromId, String toId, long amount) {
        // Check if fromId account exists
        // Cannot transfer from non-existing account
        if (!accounts.containsKey(fromId)) {
            // Source account not found, return -1
            return -1L;
        }

        // Check if toId account exists
        // Cannot transfer to non-existing account
        if (!accounts.containsKey(toId)) {
            // Destination account not found, return -1
            return -1L;
        }

        // Check if fromId and toId are the same
        // Cannot transfer to yourself
        if (fromId.equals(toId)) {
            // Same account transfer not allowed, return -1
            return -1L;
        }

        // Get current balance of source account
        long fromBalance = accounts.get(fromId);

        // Check if source account has sufficient funds
        // Cannot transfer more than available balance
        if (fromBalance < amount) {
            // Insufficient funds, return -1
            return -1L;
        }

        // Get current balance of destination account
        long toBalance = accounts.get(toId);

        // Deduct amount from source account
        // New balance = current balance - transfer amount
        long newFromBalance = fromBalance - amount;

        // Add amount to destination account
        // New balance = current balance + transfer amount
        long newToBalance = toBalance + amount;

        // Update source account with new balance
        accounts.put(fromId, newFromBalance);

        // Update destination account with new balance
        accounts.put(toId, newToBalance);

        // Return the new balance of source account
        return newFromBalance;
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
            } else if (operationType.equals("TRANSFER")) {
                // Get source account id from second part
                String fromId = parts[1];
                // Get destination account id from third part
                String toId = parts[2];
                // Get transfer amount from fourth part
                long amount = Long.parseLong(parts[3]);
                // Call transfer method and add result as string
                results.add(String.valueOf(transfer(fromId, toId, amount)));
            }
        }
        // Return all results
        return results;
    }
}