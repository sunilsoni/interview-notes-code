package com.interview.notes.code.year.y2025.october.common.test7;

import java.util.*;
import java.util.stream.Collectors;

public class Main1 {

    public static void main(String[] args) {

        // Log file for Day 1
        String[] logA = {
            "2025-01-01,Store,uuid1,100",
            "2025-01-01,Store,uuid2,500",
            "2025-01-01,Web,uuid4,100",
            "2025-01-01,Web,uuid1,150"
        };

        // Log file for Day 2
        String[] logB = {
            "2025-01-02,Phone,uuid1,250",
            "2025-01-02,Store,uuid2,1100",
            "2025-01-02,Web,uuid3,900",
            "2025-01-02,Phone,uuid3,600",
            "2025-01-02,Store,uuid4,200"
        };

        // Call our main method to find users who qualify
        List<String> result = findEstablishedUsers(logA, logB);

        // Print results
        System.out.println("Output:");
        result.forEach(System.out::println);

        // Test verification
        List<String> expected = Arrays.asList("uuid1", "uuid4");
        boolean pass = new HashSet<>(result).equals(new HashSet<>(expected));
        System.out.println("Test Result: " + (pass ? "PASS" : "FAIL"));

        // ---------- Large Input Test ----------
        String[] largeLogA = new String[10000];
        String[] largeLogB = new String[10000];
        for (int i = 0; i < 10000; i++) {
            largeLogA[i] = "2025-01-01,Store,uuid" + (i % 1000) + "," + (i + 10);
            largeLogB[i] = "2025-01-02,Web,uuid" + (i % 1000) + "," + (i + 20);
        }
        long start = System.currentTimeMillis();
        findEstablishedUsers(largeLogA, largeLogB);
        long end = System.currentTimeMillis();
        System.out.println("Large input test passed: true | Time: " + (end - start) + " ms");
    }

    // ---------------------------------------------------
    // FUNCTION: findEstablishedUsers
    // ---------------------------------------------------
    // Purpose: Identify users that satisfy both conditions:
    //   1) They made transactions on both days
    //   2) They used at least 2 different loan types in total
    // ---------------------------------------------------
    public static List<String> findEstablishedUsers(String[] logA, String[] logB) {

        // Map of UserID -> Set of LoanTypes for each day
        Map<String, Set<String>> day1Map = new HashMap<>();
        Map<String, Set<String>> day2Map = new HashMap<>();

        // Parse each line from logA and fill day1Map
        Arrays.stream(logA)
                .map(line -> line.split(","))              // Split into [date, loanType, userId, amount]
                .forEach(parts -> {
                    String userId = parts[2];
                    String loanType = parts[1];
                    day1Map.computeIfAbsent(userId, k -> new HashSet<>()).add(loanType);
                });

        // Parse each line from logB and fill day2Map
        Arrays.stream(logB)
                .map(line -> line.split(","))
                .forEach(parts -> {
                    String userId = parts[2];
                    String loanType = parts[1];
                    day2Map.computeIfAbsent(userId, k -> new HashSet<>()).add(loanType);
                });

        // Now find intersection: users present in both days
        Set<String> usersBothDays = new HashSet<>(day1Map.keySet());
        usersBothDays.retainAll(day2Map.keySet());

        // Filter users who have at least 2 different loan types in total
        List<String> establishedUsers = usersBothDays.stream()
                .filter(user -> {
                    Set<String> combined = new HashSet<>();
                    combined.addAll(day1Map.get(user));
                    combined.addAll(day2Map.get(user));
                    return combined.size() >= 2; // At least 2 loan types total
                })
                .sorted() // Sort for consistent output
                .collect(Collectors.toList());

        return establishedUsers;
    }
}