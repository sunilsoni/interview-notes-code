package com.interview.notes.code.year.y2025.december.common.test1;

public class PouringWater {

    // Main solution method that processes input and returns result
    public static String codeHere(StringBuilder inputData) {
        // Split input into lines for processing
        String[] lines = inputData.toString().trim().split("\n");

        // First line contains number of test cases
        int t = Integer.parseInt(lines[0].trim());

        // StringBuilder to collect all results
        StringBuilder result = new StringBuilder();

        // Track current line being read
        int lineIndex = 1;

        // Process each test case one by one
        for (int i = 0; i < t; i++) {
            // Check if we have enough lines remaining
            if (lineIndex + 2 >= lines.length) {
                // Not enough data, break out
                break;
            }

            // Read capacity of first vessel
            int a = Integer.parseInt(lines[lineIndex++].trim());

            // Read capacity of second vessel
            int b = Integer.parseInt(lines[lineIndex++].trim());

            // Read target amount we need to achieve
            int c = Integer.parseInt(lines[lineIndex++].trim());

            // Calculate minimum steps for this test case
            int answer = findMinSteps(a, b, c);

            // Append result to output
            if (result.length() > 0) {
                result.append("\n");
            }
            result.append(answer);
        }

        // Return final result string
        return result.toString();
    }

    // Find minimum steps to get exactly c liters
    static int findMinSteps(int a, int b, int c) {
        // If target is 0, no steps needed - already empty
        if (c == 0) {
            return 0;
        }

        // If target exceeds total capacity, impossible
        if (c > a + b) {
            return -1;
        }

        // c must be divisible by GCD for solution to exist
        // This is based on Bezout's identity theorem
        if (c % findGCD(a, b) != 0) {
            return -1;
        }

        // If target equals one vessel capacity, just fill it
        if (c == a || c == b) {
            return 1;
        }

        // If target equals sum of both, fill both vessels
        if (c == a + b) {
            return 2;
        }

        // Try strategy 1: Fill vessel A first, pour to B
        int steps1 = simulatePouringStrategy(a, b, c);

        // Try strategy 2: Fill vessel B first, pour to A
        int steps2 = simulatePouringStrategy(b, a, c);

        // Handle cases where one strategy fails
        if (steps1 == -1) return steps2;
        if (steps2 == -1) return steps1;

        // Return minimum of both strategies
        return Math.min(steps1, steps2);
    }

    // Simulate pouring water from first vessel to second
    static int simulatePouringStrategy(int capacityA, int capacityB, int target) {
        // Current water level in vessel A
        int waterInA = 0;

        // Current water level in vessel B
        int waterInB = 0;

        // Count steps taken so far
        int steps = 0;

        // Maximum iterations to prevent infinite loop
        // Using larger limit for large inputs
        int maxIterations = 2 * (capacityA + capacityB);

        // Keep pouring until we reach target
        while (steps < maxIterations) {
            // Check if we achieved target in any configuration
            if (waterInA == target || waterInB == target || waterInA + waterInB == target) {
                return steps;
            }

            // If vessel A is empty, fill it (step 1: fill)
            if (waterInA == 0) {
                waterInA = capacityA;
                steps++;
            }
            // If vessel B is full, empty it (step 2: empty)
            else if (waterInB == capacityB) {
                waterInB = 0;
                steps++;
            }
            // Otherwise pour from A to B (step 3: pour)
            else {
                // Calculate how much water can be poured
                int spaceInB = capacityB - waterInB;
                int pourAmount = Math.min(waterInA, spaceInB);

                // Transfer water from A to B
                waterInA = waterInA - pourAmount;
                waterInB = waterInB + pourAmount;
                steps++;
            }
        }

        // If we exceeded max iterations, solution not found
        return -1;
    }

    // Calculate Greatest Common Divisor using Euclidean algorithm
    static int findGCD(int num1, int num2) {
        // Keep dividing until remainder is zero
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        // GCD is the last non-zero value
        return num1;
    }

    // Test method to verify all test cases
    public static void main(String[] args) {
        System.out.println("=== Running All Test Cases ===\n");

        int passed = 0;
        int failed = 0;

        // Test Case 1: Sample input from problem (corrected with 7 values)
        // t=2, test1=(5,2,3), test2=(2,3,1)
        String input1 = "2\n5\n2\n3\n2\n3\n1";
        String expected1 = "2\n2";
        String result1 = codeHere(new StringBuilder(input1));
        if (result1.equals(expected1)) {
            System.out.println("Test 1: PASS");
            passed++;
        } else {
            System.out.println("Test 1: FAIL");
            System.out.println("  Expected: " + expected1.replace("\n", ", "));
            System.out.println("  Got: " + result1.replace("\n", ", "));
            failed++;
        }

        // Test Case 2: Single test - (5,2,3) should give 2
        String input2 = "1\n5\n2\n3";
        String expected2 = "2";
        String result2 = codeHere(new StringBuilder(input2));
        if (result2.equals(expected2)) {
            System.out.println("Test 2: PASS (5,2,3 -> 2 steps)");
            passed++;
        } else {
            System.out.println("Test 2: FAIL");
            System.out.println("  Expected: " + expected2 + ", Got: " + result2);
            failed++;
        }

        // Test Case 3: Target is zero
        String input3 = "1\n5\n3\n0";
        String expected3 = "0";
        String result3 = codeHere(new StringBuilder(input3));
        if (result3.equals(expected3)) {
            System.out.println("Test 3: PASS (target zero)");
            passed++;
        } else {
            System.out.println("Test 3: FAIL");
            System.out.println("  Expected: " + expected3 + ", Got: " + result3);
            failed++;
        }

        // Test Case 4: Target equals one vessel
        String input4 = "1\n5\n3\n5";
        String expected4 = "1";
        String result4 = codeHere(new StringBuilder(input4));
        if (result4.equals(expected4)) {
            System.out.println("Test 4: PASS (target equals vessel capacity)");
            passed++;
        } else {
            System.out.println("Test 4: FAIL");
            System.out.println("  Expected: " + expected4 + ", Got: " + result4);
            failed++;
        }

        // Test Case 5: Target equals sum of both vessels
        String input5 = "1\n5\n3\n8";
        String expected5 = "2";
        String result5 = codeHere(new StringBuilder(input5));
        if (result5.equals(expected5)) {
            System.out.println("Test 5: PASS (target equals sum)");
            passed++;
        } else {
            System.out.println("Test 5: FAIL");
            System.out.println("  Expected: " + expected5 + ", Got: " + result5);
            failed++;
        }

        // Test Case 6: Impossible case - target too large
        String input6 = "1\n5\n3\n10";
        String expected6 = "-1";
        String result6 = codeHere(new StringBuilder(input6));
        if (result6.equals(expected6)) {
            System.out.println("Test 6: PASS (impossible - too large)");
            passed++;
        } else {
            System.out.println("Test 6: FAIL");
            System.out.println("  Expected: " + expected6 + ", Got: " + result6);
            failed++;
        }

        // Test Case 7: Impossible case - not divisible by GCD
        String input7 = "1\n6\n4\n3";
        String expected7 = "-1";
        String result7 = codeHere(new StringBuilder(input7));
        if (result7.equals(expected7)) {
            System.out.println("Test 7: PASS (impossible - GCD issue)");
            passed++;
        } else {
            System.out.println("Test 7: FAIL");
            System.out.println("  Expected: " + expected7 + ", Got: " + result7);
            failed++;
        }

        // Test Case 8: Classic 3-5 jug problem to get 4
        String input8 = "1\n5\n3\n4";
        String expected8 = "6";
        String result8 = codeHere(new StringBuilder(input8));
        if (result8.equals(expected8)) {
            System.out.println("Test 8: PASS (classic 3-5 jug)");
            passed++;
        } else {
            System.out.println("Test 8: FAIL");
            System.out.println("  Expected: " + expected8 + ", Got: " + result8);
            failed++;
        }

        // Test Case 9: Large data input test
        System.out.println("\n--- Large Data Test ---");
        long startTime = System.currentTimeMillis();
        String input9 = "1\n40000\n39999\n1";
        String result9 = codeHere(new StringBuilder(input9));
        long endTime = System.currentTimeMillis();
        System.out.println("Test 9: Large input (40000, 39999, target 1)");
        System.out.println("  Result: " + result9);
        System.out.println("  Time: " + (endTime - startTime) + "ms");
        if (!result9.equals("-1")) {
            System.out.println("  Status: PASS (completed without timeout)");
            passed++;
        } else {
            System.out.println("  Status: FAIL");
            failed++;
        }

        // Test Case 10: Another large data test
        String input10 = "1\n10000\n7000\n3000";
        String result10 = codeHere(new StringBuilder(input10));
        // GCD(10000, 7000) = 1000, 3000 % 1000 = 0, so possible
        if (!result10.equals("-1")) {
            System.out.println("Test 10: PASS (large input feasible)");
            passed++;
        } else {
            System.out.println("Test 10: FAIL");
            failed++;
        }

        // Test Case 11: Equal vessels
        String input11 = "1\n5\n5\n5";
        String expected11 = "1";
        String result11 = codeHere(new StringBuilder(input11));
        if (result11.equals(expected11)) {
            System.out.println("Test 11: PASS (equal vessels)");
            passed++;
        } else {
            System.out.println("Test 11: FAIL");
            System.out.println("  Expected: " + expected11 + ", Got: " + result11);
            failed++;
        }

        // Test Case 12: Multiple test cases large batch
        StringBuilder largeBatch = new StringBuilder();
        largeBatch.append("100\n");
        for (int i = 0; i < 100; i++) {
            largeBatch.append("1000\n500\n250\n");
        }
        long start12 = System.currentTimeMillis();
        String result12 = codeHere(new StringBuilder(largeBatch));
        long end12 = System.currentTimeMillis();
        String[] results12 = result12.split("\n");
        if (results12.length == 100) {
            System.out.println("Test 12: PASS (100 test cases batch, time: " + (end12 - start12) + "ms)");
            passed++;
        } else {
            System.out.println("Test 12: FAIL (expected 100 results, got " + results12.length + ")");
            failed++;
        }

        // Print final summary
        System.out.println("\n=== Test Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total: " + (passed + failed));

        if (failed == 0) {
            System.out.println("\nALL TESTS PASSED!");
        } else {
            System.out.println("\nSOME TESTS FAILED!");
        }
    }
}