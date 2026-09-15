package com.interview.notes.code.year.y2026.august.amazon.test5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class ConsecutiveBagMoney {
    static final long MOD = 1_000_000_007L;

    public static int calculateMaximumConsecutiveSum(int k, List<List<Integer>> segment) {
        var a = segment.stream()
                .map(x -> new long[]{x.get(0), x.get(1), x.get(2)})
                .toArray(long[][]::new);

        Arrays.sort(a, Comparator.comparingLong(x -> x[0]));

        var starts = new long[a.length];
        var prefix = new long[a.length + 1];

        for (int i = 0; i < a.length; i++) {
            starts[i] = a[i][0];
            prefix[i + 1] = prefix[i] + (a[i][1] - a[i][0] + 1) * a[i][2];
        }

        long best = 0;

        for (var s : a) {
            best = Math.max(best,
                    sum(s[0], s[0] + k - 1L, a, starts, prefix));

            long left = Math.max(1, s[1] - k + 1L);

            best = Math.max(best,
                    sum(left, left + k - 1L, a, starts, prefix));
        }

        return (int) (best % MOD);
    }

    static long sum(long l, long r, long[][] a, long[] starts, long[] prefix) {
        return value(r, a, starts, prefix)
                - value(l - 1, a, starts, prefix);
    }

    static long value(long x, long[][] a, long[] starts, long[] prefix) {
        int i = Arrays.binarySearch(starts, x);

        if (i < 0) {
            i = -i - 2;
        }

        if (i < 0) {
            return 0;
        }

        return prefix[i]
                + Math.max(0, Math.min(x, a[i][1]) - a[i][0] + 1) * a[i][2];
    }

    static void test(String name, int expected, int actual) {
        System.out.println(
                name + ": " + (expected == actual ? "PASS" : "FAIL " + actual)
        );
    }

    public static void main(String[] args) {
        test(
                "Sample 0",
                15,
                calculateMaximumConsecutiveSum(
                        3,
                        List.of(
                                List.of(1, 9, 5),
                                List.of(10, 20, 5)
                        )
                )
        );

        test(
                "Sample 1",
                30,
                calculateMaximumConsecutiveSum(
                        1,
                        List.of(
                                List.of(1, 1, 10),
                                List.of(2, 2, 20),
                                List.of(3, 3, 30)
                        )
                )
        );

        test(
                "Example",
                16,
                calculateMaximumConsecutiveSum(
                        5,
                        List.of(
                                List.of(1, 4, 2),
                                List.of(6, 6, 5),
                                List.of(7, 7, 7),
                                List.of(9, 10, 1)
                        )
                )
        );

        test(
                "Large Range",
                (int) ((1_000_000_000L * 1_000_000L) % MOD),
                calculateMaximumConsecutiveSum(
                        1_000_000_000,
                        List.of(
                                List.of(1, 1_000_000_000, 1_000_000)
                        )
                )
        );

        var large = IntStream.range(0, 200_000)
                .mapToObj(i -> List.of(
                        i * 2 + 1,
                        i * 2 + 1,
                        1_000_000
                ))
                .toList();

        test(
                "Large N",
                1_000_000,
                calculateMaximumConsecutiveSum(1, large)
        );
    }
}