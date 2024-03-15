package com.interview.notes.code.months.march24.test10;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Outcome {

    public static List<Integer> solve(int m, int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = m; i <= n; i++) {
            BigInteger factorial = calculateFactorial(i);
            if (isFirstDigitEven(factorial)) {
                result.add(i);
            }
        }

        // If no factorials start with an even number, return a list containing only 0
        if (result.isEmpty()) {
            result.add(0);
        }

        return result;
    }

    private static BigInteger calculateFactorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    private static boolean isFirstDigitEven(BigInteger number) {
        int firstDigit = Integer.parseInt(number.toString().substring(0, 1));
        return firstDigit % 2 == 0;
    }

    // Main method for testing
    public static void main(String[] args) {
        List<Integer> result1 = solve(1, 10);
        System.out.println(result1); // Expected: [2, 3, 4, 8]

        List<Integer> result2 = solve(5, 7);
        System.out.println(result2); // Expected: [0]
    }
}
