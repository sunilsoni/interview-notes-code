package com.interview.notes.code.year.y2025.november.ibm.test5;

import java.util.List;
import java.util.stream.LongStream;

class ArrayGeneratorValidator {

    public static long getKCount(long s) {
        return LongStream.rangeClosed(1, (long) Math.sqrt(2 * s) + 1)
                .filter(n -> {
                    var num = 2 * s - n * (n - 1);
                    return num > 0 && num % (2 * n) == 0;
                })
                .count();
    }

    public static void main(String[] args) {
        record Test(long s, long expected) {
        }

        var tests = List.of(
                new Test(125L, 4L),
                new Test(5L, 2L),
                new Test(10L, 2L),
                new Test(1L, 1L),
                new Test(3L, 2L),
                new Test(6L, 2L),
                new Test(15L, 4L),
                new Test(21L, 4L),
                new Test(28L, 2L),
                new Test(36L, 3L),
                new Test(45L, 6L),
                new Test(55L, 4L),
                new Test(66L, 4L),
                new Test(78L, 4L),
                new Test(91L, 4L),
                new Test(105L, 8L),
                new Test(120L, 4L),
                new Test(1000L, 4L),
                new Test(10000L, 5L),
                new Test(100000L, 6L)
        );

        var start = System.nanoTime();

        tests.stream().forEach(t -> {
            var result = getKCount(t.s);
            System.out.printf("s=%d: Expected=%d, Got=%d [%s]%n",
                    t.s, t.expected, result,
                    result == t.expected ? "PASS" : "FAIL");
        });

        System.out.printf("%nTime: %.2fms%n", (System.nanoTime() - start) / 1e6);

        System.out.printf("%nLarge Input Test (s=1000000000000): %d%n", getKCount(1000000000000L));
    }
}