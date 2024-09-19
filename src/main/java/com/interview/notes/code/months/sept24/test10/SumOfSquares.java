package com.interview.notes.code.months.sept24.test10;

import java.util.Arrays;
import java.util.List;

public class SumOfSquares {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        long startTime = System.nanoTime();

        int sumOfSquares = numbers.stream()
                                  .map(n -> n * n)
                                  .reduce(0, Integer::sum);

        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        System.out.println("Sum of squares: " + sumOfSquares);
        System.out.println("Execution time in nanoseconds: " + duration);
    }
}
