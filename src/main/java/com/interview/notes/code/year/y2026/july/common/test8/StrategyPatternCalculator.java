package com.interview.notes.code.year.y2026.july.common.test8;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StrategyPatternCalculator {
    static final Map<String, Operation> OPERATIONS = Map.of(
            "sum", new SumOperation(),
            "subtract", new SubtractOperation()
    );

    public static int calculate(String s) {
        if (s == null || s.trim().isEmpty()) {
            return 0;
        }

        String[] parts = s.split(",");

        int[] numbers = Arrays.stream(parts)
                .skip(1)
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();

        return OPERATIONS
                .getOrDefault(parts[0].trim().toLowerCase(), new EmptyOperation())
                .calculate(numbers);
    }

    static void test(String name, String input, int expected) {
        int actual = calculate(input);
        System.out.println(name + ": " + (actual == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test("Sum", "sum,1,2,3,4", 10);
        test("Subtract", "subtract,1,2,3,4", -8);
        test("Invalid operation", "multiply,1,2,3,4", 0);
        test("Single number", "subtract,10", 10);
        test("No numbers", "sum", 0);
        test("Spaces", " sum, 5, 10, 15 ", 30);
        test("Empty input", "", 0);
        test("Null input", null, 0);

        String largeInput = "sum," + IntStream.range(0, 100000)
                .mapToObj(i -> "1")
                .collect(Collectors.joining(","));

        test("Large input", largeInput, 100000);
    }

    interface Operation {
        int calculate(int[] numbers);
    }

    static class SumOperation implements Operation {
        public int calculate(int[] numbers) {
            return Arrays.stream(numbers).sum();
        }
    }

    static class SubtractOperation implements Operation {
        public int calculate(int[] numbers) {
            return numbers.length == 0
                    ? 0
                    : Arrays.stream(numbers)
                            .skip(1)
                            .reduce(numbers[0], (a, b) -> a - b);
        }
    }

    static class EmptyOperation implements Operation {
        public int calculate(int[] numbers) {
            return 0;
        }
    }
}