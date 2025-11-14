package com.interview.notes.code.year.y2025.november.oci.test1;

import java.util.*;
import java.util.stream.Collectors;

public class SignInSignOutLogs {

    /**
     * processLogs:
     * -------------
     * This method processes user sign-in/sign-out logs.
     * A user can have multiple sessions. Each sign-out pairs with the latest unmatched sign-in.
     * The user is included in the result if ANY of their sessions have (signOut - signIn) <= maxSpan.
     *
     * @param logs    list of log entries, e.g. "30 15 sign-in"
     * @param maxSpan max time allowed between sign-in and sign-out (in seconds)
     * @return list of userIds (Strings) sorted ascendingly that satisfy the condition
     */
    public static List<String> processLogs(List<String> logs, int maxSpan) {

        // Tracks latest active sign-in for each user
        Map<String, Integer> activeSignIn = new HashMap<>();

        // Tracks smallest session delta for each user (min session length)
        Map<String, Integer> minValidSession = new HashMap<>();

        System.out.println("📜 Parsing and processing logs...");

        // Process each log entry sequentially
        for (String log : logs) {
            String[] parts = log.split(" ");
            String userId = parts[0];
            int timestamp = Integer.parseInt(parts[1]);
            String action = parts[2];

            if (action.equals("sign-in")) {
                // User signed in — overwrite previous if still active
                activeSignIn.put(userId, timestamp);
                System.out.println("➡️  " + userId + " signed in at " + timestamp);
            } else if (action.equals("sign-out")) {
                if (activeSignIn.containsKey(userId)) {
                    int signInTime = activeSignIn.get(userId);

                    // Ensure timestamp order (ignore corrupted logs)
                    if (timestamp > signInTime) {
                        int delta = timestamp - signInTime;
                        System.out.println("⬅️  " + userId + " signed out at " + timestamp + " | Δ = " + delta);

                        // Keep the smallest session duration per user
                        minValidSession.merge(userId, delta, Math::min);
                    } else {
                        System.out.println("⚠️  Ignored invalid event for " + userId + ": sign-out before sign-in.");
                    }

                    // End the current session
                    activeSignIn.remove(userId);
                } else {
                    // No active sign-in found (possible malformed log)
                    System.out.println("⚠️  " + userId + " sign-out ignored — no matching sign-in.");
                }
            }
        }

        System.out.println("\n🔍 Evaluating users with Δ ≤ " + maxSpan + " seconds:");

        // Filter users who had at least one valid session (min Δ ≤ maxSpan)
        List<String> validUsers = minValidSession.entrySet().stream()
                .filter(e -> e.getValue() <= maxSpan)
                .peek(e -> System.out.println("✅ User " + e.getKey() + " valid (Δ=" + e.getValue() + ")"))
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparingInt(Integer::parseInt)) // numeric sort
                .collect(Collectors.toList());

        System.out.println("\n🏁 Final Valid Users: " + validUsers);
        return validUsers;
    }

    // --------------------------------------------------------------------
    // 🧪 TESTING METHOD — Simple main method, no JUnit, just PASS/FAIL
    // --------------------------------------------------------------------
    public static void main(String[] args) {

        // Helper function for validation
        java.util.function.BiConsumer<List<String>, List<String>> test = (actual, expected) -> {
            boolean pass = actual.equals(expected);
            System.out.println("Expected: " + expected + " | Actual: " + actual + " | Result: " + (pass ? "PASS ✅" : "FAIL ❌"));
        };

        // -------------------------------------------------------------
        // TEST 1 — Standard Example
        // -------------------------------------------------------------
        System.out.println("\n===== TEST 1 =====");
        List<String> logs1 = Arrays.asList(
                "99 1 sign-in", "100 10 sign-in", "50 20 sign-in",
                "100 15 sign-out", "50 26 sign-out", "99 2 sign-out"
        );
        test.accept(processLogs(logs1, 5), Arrays.asList("99", "100"));

        // -------------------------------------------------------------
        // TEST 2 — Multiple Sign-ins Before Sign-out
        // -------------------------------------------------------------
        System.out.println("\n===== TEST 2 =====");
        List<String> logs2 = Arrays.asList(
                "30 0 sign-in",
                "30 10 sign-in",
                "30 15 sign-in",
                "30 50 sign-out",   // should match last sign-in (Δ=35)
                "60 12 sign-in",
                "60 20 sign-out"    // Δ=8
        );
        test.accept(processLogs(logs2, 40), Arrays.asList("30", "60"));

        // -------------------------------------------------------------
        // TEST 3 — Problem Statement Example
        // -------------------------------------------------------------
        System.out.println("\n===== TEST 3 =====");
        List<String> logs3 = Arrays.asList(
                "30 99 sign-in", "30 105 sign-out",
                "12 100 sign-in", "20 80 sign-in",
                "12 120 sign-out", "20 101 sign-out", "21 110 sign-in"
        );
        test.accept(processLogs(logs3, 20), Arrays.asList("12", "30"));

        // -------------------------------------------------------------
        // TEST 4 — Your Case (Multiple Sessions per User)
        // -------------------------------------------------------------
        System.out.println("\n===== TEST 4 (Multiple Sessions) =====");
        List<String> logs4 = Arrays.asList(
                "30 0 sign-in",
                "30 10 sign-out",  // Δ = 10 ✅
                "30 15 sign-in",
                "30 50 sign-out"   // Δ = 35 ❌
        );
        int maxSpan4 = 20;
        test.accept(processLogs(logs4, maxSpan4), List.of("30")); // valid because 10 ≤ 20

        // -------------------------------------------------------------
        // TEST 5 — Large Dataset Performance
        // -------------------------------------------------------------
        System.out.println("\n===== TEST 5 (Large Data Performance) =====");
        List<String> largeLogs = new ArrayList<>();
        int n = 100000;
        for (int i = 1; i <= n; i++) {
            largeLogs.add(i + " " + (i * 10) + " sign-in");
            largeLogs.add(i + " " + (i * 10 + 5) + " sign-out");
        }
        List<String> actualLarge = processLogs(largeLogs, 10);
        System.out.println("Large Data Test → Users Found: " + actualLarge.size() + " | PASS ✅ if efficient");
    }
}
