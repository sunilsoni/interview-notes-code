package com.interview.notes.code.year.y2025.november.oci.test4;

import java.util.*;
import java.util.stream.Collectors;

public class SignInSignOutLogs {

    // Function to process logs and return list of user IDs who signed out within maxSpan
    public static List<String> processLogs(List<String> logs, int maxSpan) {

        // Map to store each user's sign-in and sign-out timestamps
        Map<String, Integer> signInMap = new HashMap<>();
        Map<String, Integer> signOutMap = new HashMap<>();

        // Parse each log entry: "userId timestamp action"
        for (String log : logs) {
            String[] parts = log.split(" ");
            String userId = parts[0];
            int timestamp = Integer.parseInt(parts[1]);
            String action = parts[2];

            // Store sign-in and sign-out times separately
            if (action.equals("sign-in")) {
                signInMap.put(userId, timestamp);
            } else if (action.equals("sign-out")) {
                signOutMap.put(userId, timestamp);
            }
        }

        // Now calculate the time difference for users who have both sign-in and sign-out
        List<String> result = signInMap.keySet().stream()
                .filter(userId -> signOutMap.containsKey(userId)) // user must have signed out
                .filter(userId -> {
                    int signIn = signInMap.get(userId);
                    int signOut = signOutMap.get(userId);
                    int delta = signOut - signIn;
                    return delta <= maxSpan; // keep only if within allowed maxSpan
                })
                .sorted(Comparator.comparingInt(Integer::parseInt)) // sort numerically
                .collect(Collectors.toList());

        return result;
    }

    // ======== TESTING METHOD ========
    public static void main(String[] args) {

        // Helper lambda to print PASS/FAIL
        java.util.function.BiConsumer<List<String>, List<String>> test = (actual, expected) -> {
            boolean pass = actual.equals(expected);
            System.out.println("Expected: " + expected + " | Actual: " + actual + " | Result: " + (pass ? "PASS ✅" : "FAIL ❌"));
        };

        // ---------- Test Case 1 ----------
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

        // ---------- Test Case 2 ----------
        List<String> logs2 = Arrays.asList(
                "60 12 sign-in",
                "80 20 sign-out",
                "10 20 sign-in",
                "60 20 sign-out"
        );
        int maxSpan2 = 100;
        List<String> expected2 = List.of("60");
        test.accept(processLogs(logs2, maxSpan2), expected2);

        // ---------- Test Case 3 ----------
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

        // ---------- Large Data Test ----------
        // Create large test case to check performance
        List<String> largeLogs = new ArrayList<>();
        int n = 100000;
        for (int i = 1; i <= n; i++) {
            largeLogs.add(i + " " + (i * 10) + " sign-in");
            largeLogs.add(i + " " + (i * 10 + 5) + " sign-out");
        }
        int maxSpanLarge = 10;
        List<String> actualLarge = processLogs(largeLogs, maxSpanLarge);
        System.out.println("Large Data Test Size: " + actualLarge.size() + " users | PASS ✅ if runs efficiently");
    }
}
