package com.interview.notes.code.year.y2026.july.common.test9;

import java.util.Arrays;

interface Operation {
    int calculate(int[] n);
}

class SumOperation implements Operation {
    public int calculate(int[] n) {
        return Arrays.stream(n).sum();
    }
}

class SubtractOperation implements Operation {
    public int calculate(int[] n) {
        return Arrays.stream(n).reduce((a, b) -> a - b).orElse(0);
    }
}

class EmptyOperation implements Operation {
    public int calculate(int[] n) {
        return 0;
    }
}

class Calculator {
    private final Operation strategy;

    public Calculator(String op) {
        strategy = op.equals("sum") ? new SumOperation() :
                   op.equals("subtract") ? new SubtractOperation() :
                   new EmptyOperation();
    }

    public int execute(int[] n) {
        return strategy.calculate(n);
    }
}

public class StrategyCalculator {
    public static int calculate(String S) {
        if (S == null || S.isBlank()) return 0;
        var parts = S.split(",");
        var nums = Arrays.stream(parts).skip(1).mapToInt(Integer::parseInt).toArray();
        return new Calculator(parts[0]).execute(nums);
    }

    public static void main(String[] args) {
        test("sum,1,2,3,4", 10);
        test("subtract,1,2,3,4", -8);
        test("multiply,1,2,3,4", 0);
        test("sum", 0);
        
        var largeInput = new StringBuilder("sum");
        for (int i = 0; i < 100000; i++) {
            largeInput.append(",1");
        }
        test(largeInput.toString(), 100000);
        
        var largeSubtract = new StringBuilder("subtract,100000");
        for (int i = 0; i < 100000; i++) {
            largeSubtract.append(",1");
        }
        test(largeSubtract.toString(), 0);
    }

    private static void test(String input, int expected) {
        System.out.println(calculate(input) == expected ? "PASS" : "FAIL");
    }
}