package com.interview.notes.code.year.y2025.july.common.test1;

import java.util.*;
import java.util.stream.*;

public class SecondHighestFinder {

    /** 
     * A compact immutable Account class with id, number and amount.
     */
    public static record Account(int id, String number, double amount) {}

    /**
     * Finds the second-highest distinct amount among a list of accounts.
     * @param accounts the list of Account
     * @return OptionalDouble containing the second highest amount if present
     */
    public static OptionalDouble findSecondHighest(List<Account> accounts) {
        return accounts.stream()
            .mapToDouble(Account::amount)      // extract the double amounts
            .distinct()                        // remove duplicates
            .boxed()                           // to Stream<Double> for sorting
            .sorted(Comparator.reverseOrder()) // sort descending
            .skip(1)                           // drop the first (highest)
            .mapToDouble(Double::doubleValue)  // back to DoubleStream
            .findFirst();                      // pick the next element
    }

    /** Simple helper to run a single test and print PASS/FAIL */
    private static void runTest(String testName,
                                List<Account> input,
                                OptionalDouble expected) {
        OptionalDouble actual = findSecondHighest(input);
        boolean pass = actual.equals(expected);
        System.out.printf("%-30s %s%n", testName, pass ? "PASS" : "FAIL");
        if (!pass) {
            System.out.printf("  Expected: %s, but got: %s%n",
                expected.isPresent() ? expected.getAsDouble() : "empty",
                actual.isPresent()   ? actual.getAsDouble()   : "empty");
        }
    }

    public static void main(String[] args) {
        // 1. Normal case
        List<Account> normal = List.of(
            new Account(1, "A1", 100.0),
            new Account(2, "A2", 300.0),
            new Account(3, "A3", 200.0),
            new Account(4, "A4", 300.0)   // duplicate top
        );
        runTest("Normal case", normal, OptionalDouble.of(200.0));

        // 2. All equal amounts
        List<Account> allEqual = List.of(
            new Account(1, "A1", 50.0),
            new Account(2, "A2", 50.0)
        );
        runTest("All equal amounts", allEqual, OptionalDouble.empty());

        // 3. Single element
        List<Account> single = List.of(new Account(1, "A1", 99.0));
        runTest("Single element", single, OptionalDouble.empty());

        // 4. No accounts
        runTest("Empty list", Collections.emptyList(), OptionalDouble.empty());

        // 5. Large random test (~1M entries) for performance sanity
        Random rnd = new Random(42);
        List<Account> large = IntStream.range(0, 1_000_000)
            .mapToObj(i -> new Account(i, "X"+i, rnd.nextDouble()*1_000_000))
            .collect(Collectors.toList());
        // Manually insert two known top values
        large.set(0, new Account(0, "X0", 1_000_001.0));
        large.set(1, new Account(1, "X1", 1_000_000.5));
        runTest("Large random test", large, OptionalDouble.of(1_000_000.5));
    }
}