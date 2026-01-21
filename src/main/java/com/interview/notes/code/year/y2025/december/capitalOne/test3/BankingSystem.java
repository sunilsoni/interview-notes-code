package com.interview.notes.code.year.y2025.december.capitalOne.test3;

import java.util.*;
import java.util.stream.Collectors;

public class BankingSystem {

    // Map to store account id and their balance
    // Key = accountId, Value = balance amount
    private final Map<String, Long> accounts;

    // Map to store activity indicator for each account
    // Key = accountId, Value = total activity (absolute sum of all transactions)
    private final Map<String, Long> activityTracker;

    // Constructor to initialize the banking system
    public BankingSystem() {
        // Create empty HashMap to store accounts
        // Using HashMap for O(1) lookup time
        this.accounts = new HashMap<>();
        // Create empty HashMap to track activity
        // Activity tracks sum of deposits and successful transfers
        this.activityTracker = new HashMap<>();
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
        // Basic top activity with deposits
        System.out.println("\n=== TEST CASE 2: Basic Top Activity ===");
        BankingSystem bank2 = new BankingSystem();
        String[] ops2 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 100",
                "DEPOSIT acc2 200",
                "TOP_ACTIVITY 2"
        };
        // acc2 has activity 200, acc1 has activity 100
        List<String> expected2 = List.of("true", "true", "100", "200", "acc2(200), acc1(100)");
        List<String> result2 = bank2.processOperations(ops2);
        if (result2.equals(expected2)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected2 + ", Got: " + result2);
            failCount++;
        }

        // ============ TEST CASE 3 ============
        // Top activity with successful transfers
        System.out.println("\n=== TEST CASE 3: Top Activity with Transfers ===");
        BankingSystem bank3 = new BankingSystem();
        String[] ops3 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "CREATE_ACCOUNT acc3",
                "DEPOSIT acc1 500",
                "TRANSFER acc1 acc2 200",
                "TRANSFER acc1 acc3 100",
                "TOP_ACTIVITY 3"
        };
        // acc1: 500 (deposit) + 200 (sent) + 100 (sent) = 800
        // acc2: 200 (received) = 200
        // acc3: 100 (received) = 100
        List<String> expected3 = List.of("true", "true", "true", "500", "300", "200", "acc1(800), acc2(200), acc3(100)");
        List<String> result3 = bank3.processOperations(ops3);
        if (result3.equals(expected3)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected3 + ", Got: " + result3);
            failCount++;
        }

        // ============ TEST CASE 4 ============
        // Tie breaker - alphabetical order
        System.out.println("\n=== TEST CASE 4: Tie Breaker Alphabetical ===");
        BankingSystem bank4 = new BankingSystem();
        String[] ops4 = {
                "CREATE_ACCOUNT charlie",
                "CREATE_ACCOUNT alice",
                "CREATE_ACCOUNT bob",
                "DEPOSIT charlie 100",
                "DEPOSIT alice 100",
                "DEPOSIT bob 100",
                "TOP_ACTIVITY 3"
        };
        // All have same activity 100, sort alphabetically: alice, bob, charlie
        List<String> expected4 = List.of("true", "true", "true", "100", "100", "100", "alice(100), bob(100), charlie(100)");
        List<String> result4 = bank4.processOperations(ops4);
        if (result4.equals(expected4)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected4 + ", Got: " + result4);
            failCount++;
        }

        // ============ TEST CASE 5 ============
        // N greater than number of accounts
        System.out.println("\n=== TEST CASE 5: N Greater Than Accounts ===");
        BankingSystem bank5 = new BankingSystem();
        String[] ops5 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 100",
                "TOP_ACTIVITY 5"
        };
        // Only 2 accounts, return all sorted by activity then alphabetically
        List<String> expected5 = List.of("true", "true", "100", "acc1(100), acc2(0)");
        List<String> result5 = bank5.processOperations(ops5);
        if (result5.equals(expected5)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected5 + ", Got: " + result5);
            failCount++;
        }

        // ============ TEST CASE 6 ============
        // Failed transfers NOT counted in activity
        System.out.println("\n=== TEST CASE 6: Failed Transfers Not Counted ===");
        BankingSystem bank6 = new BankingSystem();
        String[] ops6 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 100",
                "TRANSFER acc1 acc2 200",
                "TOP_ACTIVITY 2"
        };
        // Transfer fails (insufficient funds), not counted
        // acc1: 100 only, acc2: 0
        List<String> expected6 = List.of("true", "true", "100", "-1", "acc1(100), acc2(0)");
        List<String> result6 = bank6.processOperations(ops6);
        if (result6.equals(expected6)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected6 + ", Got: " + result6);
            failCount++;
        }

        // ============ TEST CASE 7 ============
        // Top activity with n=1
        System.out.println("\n=== TEST CASE 7: Top Activity N=1 ===");
        BankingSystem bank7 = new BankingSystem();
        String[] ops7 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 500",
                "DEPOSIT acc2 300",
                "TOP_ACTIVITY 1"
        };
        // Only return top 1 account
        List<String> expected7 = List.of("true", "true", "500", "300", "acc1(500)");
        List<String> result7 = bank7.processOperations(ops7);
        if (result7.equals(expected7)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected7 + ", Got: " + result7);
            failCount++;
        }

        // ============ TEST CASE 8 ============
        // Multiple deposits to same account
        System.out.println("\n=== TEST CASE 8: Multiple Deposits ===");
        BankingSystem bank8 = new BankingSystem();
        String[] ops8 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "DEPOSIT acc1 100",
                "DEPOSIT acc1 200",
                "DEPOSIT acc1 300",
                "DEPOSIT acc2 500",
                "TOP_ACTIVITY 2"
        };
        // acc1: 100+200+300 = 600, acc2: 500
        List<String> expected8 = List.of("true", "true", "100", "300", "600", "500", "acc1(600), acc2(500)");
        List<String> result8 = bank8.processOperations(ops8);
        if (result8.equals(expected8)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected8 + ", Got: " + result8);
            failCount++;
        }

        // ============ TEST CASE 9 ============
        // Transfer to non-existing account not counted
        System.out.println("\n=== TEST CASE 9: Transfer to Non-Existing Not Counted ===");
        BankingSystem bank9 = new BankingSystem();
        String[] ops9 = {
                "CREATE_ACCOUNT acc1",
                "DEPOSIT acc1 500",
                "TRANSFER acc1 acc2 100",
                "TOP_ACTIVITY 1"
        };
        // Transfer fails (acc2 doesn't exist), only deposit counts
        List<String> expected9 = List.of("true", "500", "-1", "acc1(500)");
        List<String> result9 = bank9.processOperations(ops9);
        if (result9.equals(expected9)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected9 + ", Got: " + result9);
            failCount++;
        }

        // ============ TEST CASE 10 ============
        // Transfer to same account not counted
        System.out.println("\n=== TEST CASE 10: Transfer to Self Not Counted ===");
        BankingSystem bank10 = new BankingSystem();
        String[] ops10 = {
                "CREATE_ACCOUNT acc1",
                "DEPOSIT acc1 500",
                "TRANSFER acc1 acc1 100",
                "TOP_ACTIVITY 1"
        };
        // Self transfer fails, only deposit counts
        List<String> expected10 = List.of("true", "500", "-1", "acc1(500)");
        List<String> result10 = bank10.processOperations(ops10);
        if (result10.equals(expected10)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected10 + ", Got: " + result10);
            failCount++;
        }

        // ============ TEST CASE 11 ============
        // Complex mixed operations
        System.out.println("\n=== TEST CASE 11: Complex Mixed Operations ===");
        BankingSystem bank11 = new BankingSystem();
        String[] ops11 = {
                "CREATE_ACCOUNT acc1",
                "CREATE_ACCOUNT acc2",
                "CREATE_ACCOUNT acc3",
                "DEPOSIT acc1 1000",
                "DEPOSIT acc2 500",
                "TRANSFER acc1 acc2 300",
                "TRANSFER acc2 acc3 400",
                "TRANSFER acc1 acc3 200",
                "TOP_ACTIVITY 3"
        };
        // acc1: 1000 + 300 + 200 = 1500
        // acc2: 500 + 300 + 400 = 1200
        // acc3: 400 + 200 = 600
        List<String> expected11 = List.of("true", "true", "true", "1000", "500", "700", "400", "500", "acc1(1500), acc2(1200), acc3(600)");
        List<String> result11 = bank11.processOperations(ops11);
        if (result11.equals(expected11)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected11 + ", Got: " + result11);
            failCount++;
        }

        // ============ TEST CASE 12 ============
        // Zero activity accounts with tie
        System.out.println("\n=== TEST CASE 12: Zero Activity Tie ===");
        BankingSystem bank12 = new BankingSystem();
        String[] ops12 = {
                "CREATE_ACCOUNT zeta",
                "CREATE_ACCOUNT alpha",
                "CREATE_ACCOUNT beta",
                "TOP_ACTIVITY 3"
        };
        // All have 0 activity, sort alphabetically
        List<String> expected12 = List.of("true", "true", "true", "alpha(0), beta(0), zeta(0)");
        List<String> result12 = bank12.processOperations(ops12);
        if (result12.equals(expected12)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected12 + ", Got: " + result12);
            failCount++;
        }

        // ============ TEST CASE 13 ============
        // Large data - many accounts
        System.out.println("\n=== TEST CASE 13: Large Data - Many Accounts ===");
        BankingSystem bank13 = new BankingSystem();
        int numAcc = 10000;
        // Create many accounts and deposit different amounts
        List<String> largeOps = new ArrayList<>();
        for (int i = 0; i < numAcc; i++) {
            largeOps.add("CREATE_ACCOUNT acc" + i);
        }
        for (int i = 0; i < numAcc; i++) {
            largeOps.add("DEPOSIT acc" + i + " " + (i + 1));
        }
        largeOps.add("TOP_ACTIVITY 3");
        long startTime = System.currentTimeMillis();
        List<String> result13 = bank13.processOperations(largeOps.toArray(new String[0]));
        long endTime = System.currentTimeMillis();
        // Top 3 should be acc9999(10000), acc9998(9999), acc9997(9998)
        String lastResult = result13.get(result13.size() - 1);
        String expected13Last = "acc9999(10000), acc9998(9999), acc9997(9998)";
        if (lastResult.equals(expected13Last)) {
            System.out.println("PASS (Time: " + (endTime - startTime) + "ms)");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected13Last + ", Got: " + lastResult);
            failCount++;
        }

        // ============ TEST CASE 14 ============
        // Large data - many transfers
        System.out.println("\n=== TEST CASE 14: Large Data - Many Transfers ===");
        BankingSystem bank14 = new BankingSystem();
        bank14.createAccount("main");
        bank14.createAccount("receiver");
        bank14.deposit("main", 1000000L);
        int numTrans = 5000;
        List<String> transOps = new ArrayList<>();
        for (int i = 0; i < numTrans; i++) {
            transOps.add("TRANSFER main receiver 10");
        }
        transOps.add("TOP_ACTIVITY 2");
        startTime = System.currentTimeMillis();
        List<String> result14 = bank14.processOperations(transOps.toArray(new String[0]));
        endTime = System.currentTimeMillis();
        // main: 1000000 + 5000*10 = 1050000
        // receiver: 5000*10 = 50000
        String lastResult14 = result14.get(result14.size() - 1);
        String expected14Last = "main(1050000), receiver(50000)";
        if (lastResult14.equals(expected14Last)) {
            System.out.println("PASS (Time: " + (endTime - startTime) + "ms)");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected14Last + ", Got: " + lastResult14);
            failCount++;
        }

        // ============ TEST CASE 15 ============
        // Deposit to non-existing account not counted
        System.out.println("\n=== TEST CASE 15: Deposit to Non-Existing Not Counted ===");
        BankingSystem bank15 = new BankingSystem();
        String[] ops15 = {
                "CREATE_ACCOUNT acc1",
                "DEPOSIT acc1 100",
                "DEPOSIT acc2 500",
                "TOP_ACTIVITY 1"
        };
        // acc2 doesn't exist, deposit fails
        List<String> expected15 = List.of("true", "100", "-1", "acc1(100)");
        List<String> result15 = bank15.processOperations(ops15);
        if (result15.equals(expected15)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected15 + ", Got: " + result15);
            failCount++;
        }

        // ============ TEST CASE 16 ============
        // Mixed tie and different activity
        System.out.println("\n=== TEST CASE 16: Mixed Tie and Different Activity ===");
        BankingSystem bank16 = new BankingSystem();
        String[] ops16 = {
                "CREATE_ACCOUNT delta",
                "CREATE_ACCOUNT alpha",
                "CREATE_ACCOUNT gamma",
                "CREATE_ACCOUNT beta",
                "DEPOSIT delta 100",
                "DEPOSIT alpha 200",
                "DEPOSIT gamma 100",
                "DEPOSIT beta 200",
                "TOP_ACTIVITY 4"
        };
        // alpha:200, beta:200, delta:100, gamma:100
        // Sort: alpha(200), beta(200), delta(100), gamma(100)
        List<String> expected16 = List.of("true", "true", "true", "true", "100", "200", "100", "200",
                "alpha(200), beta(200), delta(100), gamma(100)");
        List<String> result16 = bank16.processOperations(ops16);
        if (result16.equals(expected16)) {
            System.out.println("PASS");
            passCount++;
        } else {
            System.out.println("FAIL - Expected: " + expected16 + ", Got: " + result16);
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
        // Initialize activity tracker for this account to 0
        // New account has no activity yet
        activityTracker.put(accountId, 0L);
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
            // This is unsuccessful transaction, no activity added
            return -1L;
        }
        // Get current balance of the account
        long currentBalance = accounts.get(accountId);
        // Calculate new balance by adding deposit amount
        long newBalance = currentBalance + amount;
        // Update the account with new balance
        accounts.put(accountId, newBalance);

        // Update activity tracker for this account
        // Deposit is successful, so add amount to activity
        long currentActivity = activityTracker.get(accountId);
        // New activity = old activity + deposit amount
        activityTracker.put(accountId, currentActivity + amount);

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
            // Unsuccessful transaction, no activity added
            return -1L;
        }

        // Check if toId account exists
        // Cannot transfer to non-existing account
        if (!accounts.containsKey(toId)) {
            // Destination account not found, return -1
            // Unsuccessful transaction, no activity added
            return -1L;
        }

        // Check if fromId and toId are the same
        // Cannot transfer to yourself
        if (fromId.equals(toId)) {
            // Same account transfer not allowed, return -1
            // Unsuccessful transaction, no activity added
            return -1L;
        }

        // Get current balance of source account
        long fromBalance = accounts.get(fromId);

        // Check if source account has sufficient funds
        // Cannot transfer more than available balance
        if (fromBalance < amount) {
            // Insufficient funds, return -1
            // Unsuccessful transaction, no activity added
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

        // Transfer is successful, update activity for BOTH accounts
        // Update activity for source account (fromId)
        // Sender's activity increases by transfer amount
        long fromActivity = activityTracker.get(fromId);
        activityTracker.put(fromId, fromActivity + amount);

        // Update activity for destination account (toId)
        // Receiver's activity also increases by transfer amount
        long toActivity = activityTracker.get(toId);
        activityTracker.put(toId, toActivity + amount);

        // Return the new balance of source account
        return newFromBalance;
    }

    // Method to get top n most active accounts
    // Returns array of formatted strings with accountId and activity
    // Format: ["accountId1(activity1)", "accountId2(activity2)", ...]
    public List<String> topActivity(int n) {
        // Use Java Stream to process activity data
        // Step 1: Get all entries from activity tracker map
        // Step 2: Sort by activity (descending), then by accountId (ascending)
        // Step 3: Limit to top n accounts
        // Step 4: Format each entry as "accountId(activity)"
        List<String> result = activityTracker.entrySet()  // Get all account-activity pairs
                .stream()  // Convert to stream for functional processing
                .sorted(  // Sort the stream
                        // Create comparator for sorting
                        // First: compare by activity value in descending order
                        Comparator.<Map.Entry<String, Long>>comparingLong(
                                        entry -> entry.getValue()  // Get activity value for comparison
                                )
                                .reversed()  // Reverse to make it descending order (highest first)
                                // Second: if activity is same (tie), compare by accountId
                                .thenComparing(
                                        entry -> entry.getKey()  // Get accountId for alphabetical comparison
                                )
                )
                .limit(n)  // Take only top n accounts (or all if less than n exist)
                .map(entry -> entry.getKey() + "(" + entry.getValue() + ")")  // Format output
                .collect(Collectors.toList());  // Collect results into a list

        // Return the list of formatted activity strings
        return result;
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

            // Check which operation to perform using if-else chain
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
            } else if (operationType.equals("TOP_ACTIVITY")) {
                // Get number of top accounts to return
                int n = Integer.parseInt(parts[1]);
                // Call topActivity method and get list of results
                List<String> topAccounts = topActivity(n);
                // Join the list with ", " separator to create single string
                String topResult = String.join(", ", topAccounts);
                // Add formatted result to results list
                results.add(topResult);
            }
        }
        // Return all results
        return results;
    }
}