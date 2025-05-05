package com.interview.notes.code.year.y2025.april.amazon.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AmazonRewards {

    public static int countPossibleWinners(List<Integer> initialRewards) {
        int n = initialRewards.size();

        // pair: [reward, originalIndex]
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = initialRewards.get(i);
            arr[i][1] = i;
        }

        // sort descending by reward
        Arrays.sort(arr, (a, b) -> Integer.compare(b[0], a[0]));

        long[] prefixMax = new long[n];
        long cur = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            cur = Math.max(cur, arr[i][0] + (long) (i + 1));
            prefixMax[i] = cur;
        }

        long[] suffixMax = new long[n];
        cur = Long.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            cur = Math.max(cur, arr[i][0] + (long) i);
            suffixMax[i] = cur;
        }

        int winners = 0;
        for (int p = 0; p < n; p++) {
            int originalIdx = arr[p][1];
            long competitor =
                    Math.max(p > 0 ? prefixMax[p - 1] : Long.MIN_VALUE,
                            p < n - 1 ? suffixMax[p + 1] : Long.MIN_VALUE);

            long winnerTotal = initialRewards.get(originalIdx) + (long) n;
            if (winnerTotal > competitor) {
                winners++;
            }
        }
        return winners;
    }

    /* -------- simple test harness (no JUnit) -------- */
    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();

        tests.add(new TestCase(Arrays.asList(1, 3, 4), 1));   // Example 1
        tests.add(new TestCase(Arrays.asList(8, 10, 9), 2));  // Example 2
        tests.add(new TestCase(Arrays.asList(5, 7, 9, 11), 1)); // Example 3

        // large random test (n = 100 000)
        int n = 100_000;
        List<Integer> big = new Random().ints(n, 1, 100_001)
                .boxed().collect(Collectors.toList());
        tests.add(new TestCase(big, -1)); // expected unknown, just ensure no crash

        int passed = 0;
        for (int i = 0; i < tests.size(); i++) {
            int result = countPossibleWinners(tests.get(i).rewards);
            boolean ok = tests.get(i).expected == -1 || result == tests.get(i).expected;
            System.out.println("Test " + (i + 1) + ": " + (ok ? "PASS" : "FAIL")
                    + (tests.get(i).expected != -1 ? " (got " + result + ")" : ""));
            if (ok) passed++;
        }
        System.out.println(passed + "/" + tests.size() + " tests passed.");
    }

    private static class TestCase {
        List<Integer> rewards;
        int expected; // -1 means unchecked

        TestCase(List<Integer> r, int e) {
            rewards = r;
            expected = e;
        }
    }
}
