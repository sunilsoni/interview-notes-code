package com.interview.notes.code.year.y2025.feb25.randstad.test1;

import java.util.ArrayList;
import java.util.List;

public class FactorialChallenge {

    private static long factorial(int n) {
        if (n <= 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static int getFirstDigit(long num) {
        while (num >= 10) {
            num /= 10;
        }
        return (int) num;
    }

    public static List<Integer> solve(int m, int n) {
        List<Integer> result = new ArrayList<>();

        for (int i = m; i <= n; i++) {
            long fact = factorial(i);
            int firstDigit = getFirstDigit(fact);
            if (firstDigit % 2 == 0) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        test(1, 10, "4 2 3 4 8");
        test(5, 7, "0");
        test(1, 5, "3 2 3 4");
        test(8, 10, "1 8");
    }

    private static void test(int m, int n, String expected) {
        List<Integer> result = solve(m, n);
        String output;
        if (result.isEmpty()) {
            output = "0";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(result.size()).append(" ");
            for (Integer num : result) {
                sb.append(num).append(" ");
            }
            output = sb.toString().trim();
        }

        System.out.println("Input: m=" + m + ", n=" + n);
        System.out.println("Output: " + output);
        System.out.println("Expected: " + expected);
        System.out.println("Status: " + (output.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();
    }
}
