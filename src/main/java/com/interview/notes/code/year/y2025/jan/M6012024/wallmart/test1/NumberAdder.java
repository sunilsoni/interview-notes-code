package com.interview.notes.code.year.y2025.jan.M6012024.wallmart.test1;

public class NumberAdder {
    // Main solution using bitwise operators
    public static int addNumbers1(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

    public static int addNumbers(int a, int b) {
        if (b > 0) {
            while (b > 0) {
                a++;
                b--;
            }
        } else {
            while (b < 0) {
                a--;
                b++;
            }
        }
        return a;
    }


    // Alternative solution using subtraction
    public static int addNumbersAlt(int a, int b) {
        return a - (-b);
    }

    // Test method
    public static void testCase(String testName, int a, int b, int expected) {
        int result = addNumbers(a, b);
        boolean passed = (result == expected);
        System.out.printf("Test %s: %s (Input: %d, %d | Expected: %d | Got: %d)%n",
                testName, passed ? "PASS" : "FAIL", a, b, expected, result);
    }

    public static void main(String[] args) {
        System.out.println("Running tests...\n");

        // Basic tests
        testCase("Basic Positive", 5, 3, 8);
        testCase("Basic Zero", 5, 0, 5);
        testCase("Both Zero", 0, 0, 0);

        // Negative number tests
        testCase("One Negative", -5, 3, -2);
        testCase("Both Negative", -5, -3, -8);

        // Large number tests
        testCase("Large Numbers", 1000000, 2000000, 3000000);

        // Edge cases
        testCase("Max Value Test", Integer.MAX_VALUE - 1, 1, Integer.MAX_VALUE);
        testCase("Min Value Test", Integer.MIN_VALUE + 1, -1, Integer.MIN_VALUE);

        // Performance test for large numbers
        System.out.println("\nPerformance Test:");
        long startTime = System.nanoTime();
        int result = addNumbers(999999999, 888888888);
        long endTime = System.nanoTime();
        System.out.printf("Large number addition completed in %d nanoseconds%n",
                (endTime - startTime));
        System.out.println("Result: " + result);
    }
}
