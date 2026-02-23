package com.interview.notes.code.year.y2026.feb.Codility.test2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LargestSquareSticks {

    public static void main(String[] args) {
        LargestSquareSticks solver = new LargestSquareSticks();

        Stream.of(
                new TestCase(10, 21, 7),
                new TestCase(13, 11, 5),
                new TestCase(2, 1, 0),
                new TestCase(1, 8, 2),
                new TestCase(1000000000, 1000000000, 500000000),
                new TestCase(1000000000, 1, 250000000),
                new TestCase(1, 1000000000, 250000000),
                new TestCase(999999999, 999999999, 499999999)
        ).forEach(t -> {
            int result = solver.solution(t.a(), t.b());
            System.out.printf("%s -> A: %d, B: %d, Expected: %d, Actual: %d%n",
                    (result == t.expected() ? "PASS" : "FAIL"),
                    t.a(), t.b(), t.expected(), result);
        });
    }

    public int solution(int A, int B) {
        return IntStream.of(
                A / 4,
                B / 4,
                Math.min(A / 3, B),
                Math.min(A, B / 3),
                Math.min(A / 2, B / 2)
        ).max().orElse(0);
    }

    record TestCase(int a, int b, int expected) {}
}