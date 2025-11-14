package com.interview.notes.code.year.y2025.november.oci.tes2;

import java.util.*;
import java.util.stream.Collectors;

public class SignInSignOutLogs {

    /**
     * Function: processLogs
     * ----------------------
     * This version supports multiple sign-ins before sign-out.
     * Each sign-out pairs with the most recent unmatched sign-in.
     *
     * @param logs    List of logs in format "userId timestamp action"
     * @param maxSpan Maximum allowed seconds between sign-in and sign-out
     * @return Sorted list of user IDs (as strings) that satisfy the rule
     */
    public static List<String> processLogs(List<String> logs, int maxSpan) {

        // Map to track the current active sign-ins (only latest one matters)
        Map<String, Integer> activeSignIn = new HashMap<>();

        // Map to track each user's minimum time span found
        Map<String, Integer> minSessionDelta = new HashMap<>();

        System.out.println("📜 Parsing and processing logs...");

        // Step 1 – Process each log
        for (String log : logs) {
            String[] parts = log.split(" ");
            String userId = parts[0];
            int timestamp = Integer.parseInt(parts[1]);
            String action = parts[2];

            if (action.equals("sign-in")) {
                // Overwrite any earlier sign-in — keep the latest one
                activeSignIn.put(userId, timestamp);
                System.out.println("➡️  " + userId + " signed-in at " + timestamp);

            } else if (action.equals("sign-out")) {
                if (activeSignIn.containsKey(userId)) {
                    int signInTime = activeSignIn.get(userId);
                    int delta = timestamp - signInTime;
                    System.out.println("⬅️  " + userId + " signed-out at " + timestamp + " | Δ = " + delta);

                    // Store smallest delta per user (user may have multiple sessions)
                    minSessionDelta.merge(userId, delta, Math::min);

                    // Remove this sign-in as it’s now matched
                    activeSignIn.remove(userId);
                } else {
                    // Sign-out without matching sign-in (ignore safely)
                    System.out.println("⚠️  " + userId + " sign-out ignored — no active sign-in");
                }
            }
        }

        System.out.println("\n🔍 Evaluating users (Δ ≤ " + maxSpan + "):");

        // Step 2 – Filter users by condition
        List<String> validUsers = minSessionDelta.entrySet().stream()
                .filter(e -> e.getValue() <= maxSpan)
                .peek(e -> System.out.println("User " + e.getKey() + " → Δ = " + e.getValue() + " ✅"))
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());

        System.out.println("\n✅ Valid Users: " + validUsers);
        return validUsers;
    }

    // ----------------------------
    // TESTING METHOD (no JUnit)
    // ----------------------------
    public static void main(String[] args) {

        java.util.function.BiConsumer<List<String>, List<String>> test = (actual, expected) -> {
            boolean pass = actual.equals(expected);
            System.out.println("Expected: " + expected + " | Actual: " + actual + " | Result: " + (pass ? "PASS ✅" : "FAIL ❌"));
        };

        // TEST 1 – Standard problem sample
        System.out.println("\n===== TEST 1 =====");
        List<String> logs1 = Arrays.asList(
                "99 1 sign-in",
                "100 10 sign-in",
                "50 20 sign-in",
                "100 15 sign-out",
                "50 26 sign-out",
                "99 2 sign-out"
        );
        test.accept(processLogs(logs1, 5), Arrays.asList("99", "100"));

        // TEST 2 – Multiple sign-ins before sign-out
        System.out.println("\n===== TEST 2 =====");
        List<String> logs2 = Arrays.asList(
                "30 0 sign-in",
                "30 10 sign-in",
                "30 15 sign-in",
                "30 50 sign-out",    // Should pair with sign-in @ 15 → Δ = 35
                "60 12 sign-in",
                "60 20 sign-out"     // Δ = 8
        );
        test.accept(processLogs(logs2, 40), Arrays.asList("30", "60"));

        // TEST 3 – Different users, mixed order
        System.out.println("\n===== TEST 3 =====");
        List<String> logs3 = Arrays.asList(
                "30 99 sign-in", "30 105 sign-out",
                "12 100 sign-in", "20 80 sign-in",
                "12 120 sign-out", "20 101 sign-out", "21 110 sign-in"
        );
        test.accept(processLogs(logs3, 20), Arrays.asList("12", "30"));

        // TEST 4 – Large-scale data performance
        System.out.println("\n===== TEST 4 (LARGE DATA) =====");
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
