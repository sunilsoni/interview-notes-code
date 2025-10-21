package com.interview.notes.code.year.y2025.october.common.test7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // -----------------------------
        // Day 1 and Day 2 logs (same as Part 1)
        // -----------------------------
        String[] logA = {
                "2025-01-01,Store,uuid1,100",
                "2025-01-01,Store,uuid2,500",
                "2025-01-01,Web,uuid4,100",
                "2025-01-01,Web,uuid1,150"
        };
        String[] logB = {
                "2025-01-02,Phone,uuid1,250",
                "2025-01-02,Store,uuid2,1100",
                "2025-01-02,Web,uuid3,900",
                "2025-01-02,Phone,uuid3,600",
                "2025-01-02,Store,uuid4,200"
        };

        // Step 1: Find established users
        List<String> establishedUsers = findEstablishedUsers(logA, logB);
        System.out.println("Established Users: " + establishedUsers);

        // Step 2: Build user → loanTypes and amount range
        Map<String, Set<String>> userLoanTypes = new HashMap<>();
        Map<String, double[]> userAmountRange = new HashMap<>();

        Arrays.stream(Stream.concat(Arrays.stream(logA), Arrays.stream(logB))
                        .toArray(String[]::new))
                .map(line -> line.split(","))
                .forEach(parts -> {
                    String user = parts[2];
                    double amt = Double.parseDouble(parts[3]);
                    String loanType = parts[1];
                    userLoanTypes.computeIfAbsent(user, k -> new HashSet<>()).add(loanType);

                    double[] range = userAmountRange.getOrDefault(user, new double[]{amt, amt});
                    range[0] = Math.min(range[0], amt);
                    range[1] = Math.max(range[1], amt);
                    userAmountRange.put(user, range);
                });

        // Step 3: Example Trust Score evaluations
        testTrustScore("uuid4", "Web", 100, establishedUsers, userLoanTypes, userAmountRange, 100);
        testTrustScore("uuid4", "Phone", 120, establishedUsers, userLoanTypes, userAmountRange, 50);
        testTrustScore("uuid4", "Store", 80, establishedUsers, userLoanTypes, userAmountRange, 80);
        testTrustScore("uuid4", "Phone", 280, establishedUsers, userLoanTypes, userAmountRange, 10);
    }

    // -----------------------------
    // Find established users (same logic from Part 1)
    // -----------------------------
    public static List<String> findEstablishedUsers(String[] logA, String[] logB) {
        Map<String, Set<String>> day1 = new HashMap<>();
        Map<String, Set<String>> day2 = new HashMap<>();

        Arrays.stream(logA).map(s -> s.split(","))
                .forEach(p -> day1.computeIfAbsent(p[2], k -> new HashSet<>()).add(p[1]));
        Arrays.stream(logB).map(s -> s.split(","))
                .forEach(p -> day2.computeIfAbsent(p[2], k -> new HashSet<>()).add(p[1]));

        Set<String> both = new HashSet<>(day1.keySet());
        both.retainAll(day2.keySet());

        return both.stream().filter(u -> {
            Set<String> all = new HashSet<>(day1.get(u));
            all.addAll(day2.get(u));
            return all.size() >= 2;
        }).sorted().collect(Collectors.toList());
    }

    // -----------------------------
    // Compute Trust Score for a transaction
    // -----------------------------
    public static int computeTrustScore(
            String user, String loanType, double amount,
            List<String> established,
            Map<String, Set<String>> loanMap,
            Map<String, double[]> rangeMap) {

        // If not established, trust = 0
        if (!established.contains(user)) return 0;

        // LoanType Matching (50 or 0)
        int loanTypeScore = loanMap.getOrDefault(user, Collections.emptySet()).contains(loanType) ? 50 : 0;

        // Amount Threshold Calculation
        double[] bounds = rangeMap.get(user);
        double min = bounds[0], max = bounds[1];
        int amountScore = 50;

        if (amount < min) {
            double percentBelow = (min - amount) / min;
            amountScore -= (int) (percentBelow * 100 / 10) * 10;
        } else if (amount > max) {
            double percentAbove = (amount - max) / max;
            amountScore -= (int) (percentAbove * 100 / 10) * 10;
        }
        if (amountScore < 0) amountScore = 0;

        return loanTypeScore + amountScore;
    }

    // -----------------------------
    // Helper method to test & print result
    // -----------------------------
    public static void testTrustScore(String user, String loanType, double amt,
                                      List<String> established,
                                      Map<String, Set<String>> loanMap,
                                      Map<String, double[]> rangeMap,
                                      int expected) {
        int score = computeTrustScore(user, loanType, amt, established, loanMap, rangeMap);
        System.out.printf("Input(%s,%s,%.0f) => Output(%d) | Expected(%d) | %s%n",
                user, loanType, amt, score, expected, (score == expected ? "PASS" : "FAIL"));
    }
}