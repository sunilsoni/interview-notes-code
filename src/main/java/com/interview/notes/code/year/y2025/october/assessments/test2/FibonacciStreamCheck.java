package com.interview.notes.code.year.y2025.october.assessments.test2;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FibonacciStreamCheck {
    public static void main(String[] args) {
        System.out.println("n=7 → " + calculate(7));
        System.out.println("n=8 → " + calculate(8));
        System.out.println("Difference → " + (calculate(8) - calculate(7)));
    }

    private static int calculate(int n) {
        return Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .limit(n)
                .map(x -> x[0])
                .collect(Collectors.toList())
                .stream()
                .distinct()
                .filter(i -> i % 2 == 0)
                .mapToInt(i -> i)
                .sum();
    }
}
