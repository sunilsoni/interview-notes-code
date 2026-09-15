package com.interview.notes.code.year.y2026.september.amazon.test2;

import java.util.List;
import java.util.stream.IntStream;

public class MinimumMergeConflicts {

    public static int getMinimumConflicts(String primary, String secondary) {
        int m = secondary.length();
        int[] dp = new int[m + 1];
        int[] greater = new int[m];

        for (char p : primary.toCharArray()) {
            int secondaryGreater = 0;
            dp[0] = 0;

            for (int j = 1; j <= m; j++) {
                char s = secondary.charAt(j - 1);

                if (s > p) {
                    secondaryGreater++;
                }

                if (p > s) {
                    greater[j - 1]++;
                }

                dp[j] = Math.min(
                        dp[j] + secondaryGreater,
                        dp[j - 1] + greater[j - 1]
                );
            }
        }

        return dp[m] + conflicts(primary) + conflicts(secondary);
    }

    private static int conflicts(String value) {
        return IntStream.range(0, value.length())
                .map(i -> (int) IntStream.range(i + 1, value.length())
                        .filter(j -> value.charAt(i) > value.charAt(j))
                        .count())
                .sum();
    }

    static void main(String[] args) {
        var cases = List.of(
                new ConflictCase("dae", "add", 1),
                new ConflictCase("aaa", "abb", 0),
                new ConflictCase("zc", "d", 2),
                new ConflictCase("abc", "abc", 0),
                new ConflictCase("ba", "c", 1),
                new ConflictCase("cba", "z", 3),
                new ConflictCase("z", "cba", 3),
                new ConflictCase("zzz", "aaa", 0),
                new ConflictCase("a".repeat(1000), "z".repeat(1000), 0),
                new ConflictCase("z".repeat(1000), "a".repeat(1000), 0)
        );

        IntStream.range(0, cases.size()).forEach(i -> {
            var test = cases.get(i);
            int actual = getMinimumConflicts(test.primary(), test.secondary());

            System.out.printf(
                    "Test %d: %s Expected=%d Actual=%d%n",
                    i + 1,
                    actual == test.expected() ? "PASS" : "FAIL",
                    test.expected(),
                    actual
            );
        });
    }

    record ConflictCase(String primary, String secondary, int expected) {
    }
}