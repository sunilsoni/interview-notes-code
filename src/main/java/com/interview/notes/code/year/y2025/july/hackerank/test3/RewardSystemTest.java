package com.interview.notes.code.year.y2025.july.hackerank.test3;

import java.util.*;

enum TransactionType {
    P2M, P2P, Self;
}

class TransactionSummary {
    int transactionId;
    boolean isSenderEligibleForReward;

    public TransactionSummary(int transactionId, boolean isSenderEligibleForReward) {
        this.transactionId = transactionId;
        this.isSenderEligibleForReward = isSenderEligibleForReward;
    }

    @Override
    public String toString() {
        return transactionId + " " + isSenderEligibleForReward;
    }
}

class Payment {
    private static final int TOP_K = 100;
    private Map<Integer, Map<TransactionType, Integer>> txnCounts = new HashMap<>();
    private Map<Integer, Long> userP2mSum = new HashMap<>();
    private PriorityQueue<long[]> top100 = new PriorityQueue<>((a, b) -> {
        if (a[0] == b[0]) return Long.compare(a[1], b[1]);
        return Long.compare(a[0], b[0]);
    });
    private Set<Integer> top100Ids = new HashSet<>();

    private void updateTop100(int senderId, long newTotal) {
        if (top100Ids.contains(senderId)) {
            top100.removeIf(pair -> pair[1] == senderId);
            top100.add(new long[]{newTotal, senderId});
        } else {
            if (top100.size() < TOP_K) {
                top100.add(new long[]{newTotal, senderId});
                top100Ids.add(senderId);
            } else if (top100.peek()[0] < newTotal || (top100.peek()[0] == newTotal && top100.peek()[1] > senderId)) {
                long[] removed = top100.poll();
                if (removed != null) top100Ids.remove((int) removed[1]);
                top100.add(new long[]{newTotal, senderId});
                top100Ids.add(senderId);
            }
        }
    }

    public TransactionSummary makePayment(int transactionId, int senderId, int amount, TransactionType transactionType) {
        txnCounts.computeIfAbsent(senderId, k -> new EnumMap<>(TransactionType.class))
                .merge(transactionType, 1, Integer::sum);

        boolean eligible = false;

        if (transactionType == TransactionType.P2M) {
            long newTotal = userP2mSum.getOrDefault(senderId, 0L) + amount;
            userP2mSum.put(senderId, newTotal);
            updateTop100(senderId, newTotal);
            eligible = top100Ids.contains(senderId);
        }
        return new TransactionSummary(transactionId, eligible);
    }

    public int getNumberOfTransactions(int senderId, TransactionType transactionType) {
        return txnCounts.getOrDefault(senderId, Collections.emptyMap())
                .getOrDefault(transactionType, 0);
    }
}

public class RewardSystemTest {

    public static void main(String[] args) {
        runAllTests();
        testLargeInput();
    }

    // TEST CASE RUNNER
    public static void runAllTests() {
        List<TestCase> testCases = new ArrayList<>();

        // Sample Input Test
        testCases.add(new TestCase(
                Arrays.asList(
                        "makePayment 0 2 100 P2P",
                        "makePayment 1 4 18 P2M",
                        "makePayment 2 2 50 P2M",
                        "getNumberOfTransactions 2 P2P"
                ),
                Arrays.asList(
                        "0 false",
                        "1 true",
                        "2 true",
                        "1"
                ),
                "Sample Input Test"
        ));

        // Edge Case: Only one user, multiple P2M
        testCases.add(new TestCase(
                Arrays.asList(
                        "makePayment 10 99 999 P2M",
                        "makePayment 11 99 1 P2M",
                        "getNumberOfTransactions 99 P2M"
                ),
                Arrays.asList(
                        "10 true",
                        "11 true",
                        "2"
                ),
                "One User Multiple P2M"
        ));

        // Edge: Two users, tie on P2M, check lexicographical order
        testCases.add(new TestCase(
                Arrays.asList(
                        "makePayment 1 100 100 P2M",
                        "makePayment 2 200 100 P2M",
                        "makePayment 3 100 1 P2M",   // 101 for 100, 100 for 200
                        "makePayment 4 200 1 P2M",   // both now at 101
                        "getNumberOfTransactions 200 P2M"
                ),
                Arrays.asList(
                        "1 true",
                        "2 true",
                        "3 true",
                        "4 true",
                        "2"
                ),
                "Tie on P2M"
        ));

        // Run each test
        int pass = 0, fail = 0;
        for (TestCase tc : testCases) {
            List<String> actual = runTestCase(tc.inputs);
            boolean ok = compareLists(tc.expected, actual);
            if (ok) {
                System.out.println("PASS: " + tc.name);
                pass++;
            } else {
                System.out.println("FAIL: " + tc.name);
                System.out.println("Expected:");
                tc.expected.forEach(System.out::println);
                System.out.println("Actual:");
                actual.forEach(System.out::println);
                fail++;
            }
        }
        System.out.println("---- " + pass + " PASSED, " + fail + " FAILED ----");
    }

    // Large scale test
    public static void testLargeInput() {
        int N = 200_000;
        Payment payment = new Payment();
        boolean error = false;

        // 1st 100 unique users should be eligible
        for (int i = 1; i <= 100; i++) {
            TransactionSummary ts = payment.makePayment(i, i, 1000, TransactionType.P2M);
            if (!ts.isSenderEligibleForReward) {
                error = true;
                System.out.println("FAIL: User " + i + " should be eligible");
            }
        }
        // Next user, not in top 100 after adding low amount
        for (int i = 101; i <= N; i++) {
            TransactionSummary ts = payment.makePayment(i, i, 1, TransactionType.P2M);
            if (ts.isSenderEligibleForReward && i > 100) {
                error = true;
                System.out.println("FAIL: User " + i + " should NOT be eligible");
            }
        }
        // Big sender jumps into top 100
        TransactionSummary ts = payment.makePayment(N + 1, 999_999, 10_000_000, TransactionType.P2M);
        if (!ts.isSenderEligibleForReward) {
            error = true;
            System.out.println("FAIL: Big sender not marked as eligible");
        }

        if (!error) {
            System.out.println("PASS: Large Input Test");
        }
    }

    // Helper - runs a list of commands
    private static List<String> runTestCase(List<String> lines) {
        Payment payment = new Payment();
        List<String> output = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts[0].equals("makePayment")) {
                int transactionId = Integer.parseInt(parts[1]);
                int senderId = Integer.parseInt(parts[2]);
                int amount = Integer.parseInt(parts[3]);
                TransactionType tt = TransactionType.valueOf(parts[4]);
                TransactionSummary ts = payment.makePayment(transactionId, senderId, amount, tt);
                output.add(ts.toString());
            } else if (parts[0].equals("getNumberOfTransactions")) {
                int senderId = Integer.parseInt(parts[1]);
                TransactionType tt = TransactionType.valueOf(parts[2]);
                int count = payment.getNumberOfTransactions(senderId, tt);
                output.add(String.valueOf(count));
            }
        }
        return output;
    }

    // Helper - compare outputs
    private static boolean compareLists(List<String> expected, List<String> actual) {
        if (expected.size() != actual.size()) return false;
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).trim().equals(actual.get(i).trim())) {
                return false;
            }
        }
        return true;
    }

    // Test case container
    static class TestCase {
        List<String> inputs;
        List<String> expected;
        String name;

        TestCase(List<String> inputs, List<String> expected, String name) {
            this.inputs = inputs;
            this.expected = expected;
            this.name = name;
        }
    }
}
