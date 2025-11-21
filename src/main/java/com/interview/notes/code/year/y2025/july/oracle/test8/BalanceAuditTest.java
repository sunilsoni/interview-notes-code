package com.interview.notes.code.year.y2025.july.oracle.test8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BalanceAuditTest {

    // This method does the real work of finding the min‐balance members.
    public static List<String> getMinBalanceMembers(List<List<String>> transactions) {
        // Map to hold each person's net balance change.
        Map<String, Long> balances = new HashMap<>(); // name → balance

        // Go through every transaction
        for (List<String> tx : transactions) {
            // Parse the three parts
            String sender = tx.get(0);                           // who pays
            String receiver = tx.get(1);                           // who gets paid
            long amount = Long.parseLong(tx.get(2));           // how much is moved

            // Subtract from sender's balance
            balances.put(sender, balances.getOrDefault(sender, 0L) - amount);
            // Add to receiver's balance
            balances.put(receiver, balances.getOrDefault(receiver, 0L) + amount);
        }

        // Find the smallest net balance in the map
        OptionalLong maybeMin = balances.values().stream()
                .mapToLong(Long::longValue)
                .min();

        // If no one is negative, return the special message
        if (!maybeMin.isPresent() || maybeMin.getAsLong() >= 0) {
            return Collections.singletonList("Nobody has a negative balance");
        }

        long minBalance = maybeMin.getAsLong(); // this is negative

        // Collect all names that have that exact min balance, sort alphabetically
        return balances.entrySet().stream()
                .filter(e -> e.getValue() == minBalance)          // only the worst off
                .map(Map.Entry::getKey)                           // extract the names
                .sorted()                                         // alphabetical order
                .collect(Collectors.toList());
    }

    // Simple main method to run a bunch of tests and print PASS/FAIL
    public static void main(String[] args) {
        // List to hold our test definitions: each is (transactions, expectedResult)
        List<TestCase> tests = new ArrayList<>();

        // Test 1: given example from the prompt
        tests.add(new TestCase(
                Arrays.asList(
                        Arrays.asList("Alex", "Blake", "2"),
                        Arrays.asList("Blake", "Alex", "2"),
                        Arrays.asList("Casey", "Alex", "5"),
                        Arrays.asList("Blake", "Casey", "7"),
                        Arrays.asList("Alex", "Blake", "4"),
                        Arrays.asList("Alex", "Casey", "4")
                ),
                Arrays.asList("Alex", "Blake")
        ));

        // Test 2: sample case with single min (Casey)
        tests.add(new TestCase(
                Arrays.asList(
                        Arrays.asList("Alex", "Blake", "5"),
                        Arrays.asList("Blake", "Alex", "3"),
                        Arrays.asList("Casey", "Alex", "7"),
                        Arrays.asList("Casey", "Alex", "4"),
                        Arrays.asList("Casey", "Alex", "2")
                ),
                List.of("Casey")
        ));

        // Test 3: no negative balances at all
        tests.add(new TestCase(
                Arrays.asList(
                        Arrays.asList("A", "B", "5"),
                        Arrays.asList("B", "A", "5")
                ),
                Collections.singletonList("Nobody has a negative balance")
        ));

        // Test 4: tie with more names
        tests.add(new TestCase(
                Arrays.asList(
                        Arrays.asList("X", "Y", "10"),
                        Arrays.asList("Y", "Z", "10"),
                        Arrays.asList("Z", "X", "10")
                ),
                Arrays.asList("X", "Y", "Z") // each has -10+10 = 0 → nobody negative? actually 0, so special
        ));
        // Fix expected for test 4: zero is not negative
        tests.get(3).expected = Collections.singletonList("Nobody has a negative balance");

        // Test 5: large input performance test
        int largeN = 100_000;               // size of large test
        List<List<String>> bigTx = IntStream.range(0, largeN)
                .mapToObj(i -> Arrays.asList("A", "B", "1")) // always A pays B 1 unit
                .collect(Collectors.toList());
        tests.add(new TestCase(
                bigTx,
                Collections.singletonList("A")      // A will be -100k, B will be +100k
        ));

        // Run each test and print result
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);                             // get test case
            List<String> result = getMinBalanceMembers(tc.input);   // run the function
            // Compare result to expected
            boolean pass = result.equals(tc.expected);
            // Print PASS or FAIL with details
            System.out.printf("Test %d: %s\n",
                    i + 1,
                    pass ? "PASS" : "FAIL (expected=" + tc.expected + ", got=" + result + ")");
        }
    }

    // Helper class to bundle each test's input and expected output
    private static class TestCase {
        List<List<String>> input;      // list of transactions
        List<String> expected;         // expected list of names

        TestCase(List<List<String>> in, List<String> exp) {
            this.input = in;           // store transactions
            this.expected = exp;       // store expected result
        }
    }
}