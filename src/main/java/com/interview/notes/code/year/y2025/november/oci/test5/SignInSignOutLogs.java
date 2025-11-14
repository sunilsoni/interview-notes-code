package com.interview.notes.code.year.y2025.november.oci.test5;

import java.util.*;
import java.util.stream.Collectors;

public class SignInSignOutLogs {

    /**
     * Function: processLogs
     * ----------------------
     * This method reads a list of log entries (user_id timestamp action)
     * and returns a list of users who signed out within 'maxSpan' seconds of signing in.
     *
     * @param logs    List of logs in format "userId timestamp action"
     * @param maxSpan Maximum allowed time between sign-in and sign-out
     * @return Sorted list of user IDs (as Strings) who satisfy the time limit condition
     */
    public static List<String> processLogs(List<String> logs, int maxSpan) {

        // Store each user's sign-in and sign-out timestamps.
        Map<String, Integer> signInMap = new HashMap<>();
        Map<String, Integer> signOutMap = new HashMap<>();

        System.out.println("📜 Parsing logs...");
        // Step 1: Parse each log entry.
        for (String log : logs) {
            String[] parts = log.split(" "); // Split by space
            String userId = parts[0];        // e.g., "99"
            int timestamp = Integer.parseInt(parts[1]); // e.g., "1" → 1
            String action = parts[2];        // "sign-in" or "sign-out"

            // Save sign-in and sign-out times separately.
            if (action.equals("sign-in")) {
                signInMap.put(userId, timestamp);
                System.out.println("➡️ User " + userId + " signed in at " + timestamp);
            } else if (action.equals("sign-out")) {
                signOutMap.put(userId, timestamp);
                System.out.println("⬅️ User " + userId + " signed out at " + timestamp);
            }
        }

        // Step 2: Process users and find those within maxSpan.
        System.out.println("\n🔍 Checking users for valid sessions (≤ " + maxSpan + " seconds):");

        List<String> result = signInMap.keySet().stream()
                .filter(userId -> signOutMap.containsKey(userId)) // only consider users who signed out
                .filter(userId -> {
                    int signIn = signInMap.get(userId);
                    int signOut = signOutMap.get(userId);
                    int delta = signOut - signIn;
                    System.out.println("User " + userId + " → SignIn=" + signIn + ", SignOut=" + signOut + ", Δ=" + delta);
                    return delta <= maxSpan; // valid if delta ≤ maxSpan
                })
                .sorted(Comparator.comparingInt(Integer::parseInt)) // numeric sort (not lexicographic)
                .collect(Collectors.toList());

        // Step 3: Print the results
        System.out.println("\n✅ Valid Users (within maxSpan): " + result);
        return result;
    }

    /**
     * Main method to run and test multiple sample cases.
     * No JUnit, just simple PASS/FAIL validation.
     */
    public static void main(String[] args) {

        // Lambda helper to print test results
        java.util.function.BiConsumer<List<String>, List<String>> test = (actual, expected) -> {
            boolean pass = actual.equals(expected);
            System.out.println("\nExpected: " + expected + " | Actual: " + actual + " | Result: " + (pass ? "PASS ✅" : "FAIL ❌"));
        };

        // -------------------------------
        // 🧪 TEST CASE 1
        // -------------------------------
        System.out.println("\n===== TEST CASE 1 =====");
        List<String> logs1 = Arrays.asList(
                "99 1 sign-in",
                "100 10 sign-in",
                "50 20 sign-in",
                "100 15 sign-out",
                "50 26 sign-out",
                "99 2 sign-out"
        );
        int maxSpan1 = 5;
        List<String> expected1 = Arrays.asList("99", "100");
        test.accept(processLogs(logs1, maxSpan1), expected1);

        // -------------------------------
        // 🧪 TEST CASE 2
        // -------------------------------
        System.out.println("\n===== TEST CASE 2 =====");
        List<String> logs2 = Arrays.asList(
                "60 12 sign-in",
                "80 20 sign-out",
                "10 20 sign-in",
                "60 20 sign-out"
        );
        int maxSpan2 = 100;
        List<String> expected2 = List.of("60");
        test.accept(processLogs(logs2, maxSpan2), expected2);

        // -------------------------------
        // 🧪 TEST CASE 3
        // -------------------------------
        System.out.println("\n===== TEST CASE 3 =====");
        List<String> logs3 = Arrays.asList(
                "30 99 sign-in",
                "30 105 sign-out",
                "12 100 sign-in",
                "20 80 sign-in",
                "12 120 sign-out",
                "20 101 sign-out",
                "21 110 sign-in"
        );
        int maxSpan3 = 20;
        List<String> expected3 = Arrays.asList("12", "30");
        test.accept(processLogs(logs3, maxSpan3), expected3);

        // -------------------------------
        // 🧪 LARGE DATA TEST (Performance)
        // -------------------------------
        System.out.println("\n===== LARGE DATA TEST =====");
        List<String> largeLogs = new ArrayList<>();
        int n = 100000; // 100k entries
        for (int i = 1; i <= n; i++) {
            largeLogs.add(i + " " + (i * 10) + " sign-in");
            largeLogs.add(i + " " + (i * 10 + 5) + " sign-out");
        }
        int maxSpanLarge = 10;
        List<String> actualLarge = processLogs(largeLogs, maxSpanLarge);
        System.out.println("Large Data Test Completed → Users Found: " + actualLarge.size() + " | PASS ✅ if program runs efficiently.");
    }
}
