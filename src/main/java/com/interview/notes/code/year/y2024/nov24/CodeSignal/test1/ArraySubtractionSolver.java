package com.interview.notes.code.year.y2024.nov24.CodeSignal.test1;

public class ArraySubtractionSolver {

    public static int solution(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        int[] workingArray = numbers.clone();
        int result = 0;

        while (true) {
            // Step 1: Find leftmost non-zero element
            int leftmostIndex = -1;
            for (int i = 0; i < workingArray.length; i++) {
                if (workingArray[i] > 0) {
                    leftmostIndex = i;
                    break;
                }
            }

            // If no non-zero elements found, algorithm ends
            if (leftmostIndex == -1) {
                break;
            }

            // Get the value to subtract
            int valueToSubtract = workingArray[leftmostIndex];
            result += valueToSubtract;

            // Step 2: Subtract the value from remaining elements
            for (int i = leftmostIndex; i < workingArray.length; i++) {
                if (workingArray[i] < valueToSubtract) {
                    break;
                }
                workingArray[i] -= valueToSubtract;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case class
        class TestCase {
            int[] numbers;
            int expected;
            String description;

            TestCase(int[] numbers, int expected, String description) {
                this.numbers = numbers;
                this.expected = expected;
                this.description = description;
            }
        }

        // Create test cases
        TestCase[] testCases = {
                // Basic test cases
                new TestCase(new int[]{3, 3, 5, 2, 3}, 6, "Basic test case 1"),
                new TestCase(new int[]{5, 5, 5}, 5, "Basic test case 2"),

                // Edge cases
                new TestCase(new int[]{0}, 0, "Single zero"),
                new TestCase(new int[]{1}, 1, "Single non-zero"),
                new TestCase(new int[]{0, 0, 0}, 0, "All zeros"),
                new TestCase(new int[]{1000000}, 1000000, "Maximum single value"),

                // Complex cases
                new TestCase(new int[]{1, 2, 3, 4, 5}, 9, "Increasing sequence"),
                new TestCase(new int[]{5, 4, 3, 2, 1}, 5, "Decreasing sequence"),
                new TestCase(new int[]{1, 1, 1, 1, 1}, 1, "All same values"),

                // Large input cases
                new TestCase(generateLargeArray(100, 1000), -1, "Large array test"),
                new TestCase(generateLargeArray(50, 1000000), -1, "Large values test")
        };

        // Run tests
        int passedTests = 0;
        int totalTests = testCases.length;

        for (int i = 0; i < totalTests; i++) {
            TestCase tc = testCases[i];

            // Skip result verification for large input cases
            if (tc.expected == -1) {
                try {
                    long startTime = System.currentTimeMillis();
                    int result = solution(tc.numbers);
                    long endTime = System.currentTimeMillis();

                    System.out.printf("Test Case %d: %s\n", i + 1, tc.description);
                    System.out.printf("Array length: %d\n", tc.numbers.length);
                    System.out.printf("Execution time: %d ms\n", endTime - startTime);
                    System.out.printf("Result: %d -> PASS (Performance test)\n\n", result);
                    passedTests++;
                } catch (Exception e) {
                    System.out.printf("Test Case %d: %s -> FAIL (Exception)\n\n", i + 1, tc.description);
                }
                continue;
            }

            // Regular test cases
            int result = solution(tc.numbers);
            boolean passed = result == tc.expected;

            System.out.printf("Test Case %d: %s\n", i + 1, tc.description);
            System.out.print("Input: [");
            printArray(tc.numbers);
            System.out.printf("]\nExpected: %d, Got: %d -> %s\n\n",
                    tc.expected, result, passed ? "PASS" : "FAIL");

            if (passed) passedTests++;
        }

        // Print summary
        System.out.println("Test Summary:");
        System.out.printf("Passed: %d/%d tests\n", passedTests, totalTests);
        System.out.printf("Success Rate: %.2f%%\n", (passedTests * 100.0) / totalTests);
    }

    // Helper method to generate large test arrays
    private static int[] generateLargeArray(int size, int maxValue) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * maxValue);
        }
        return array;
    }

    // Helper method to print array elements
    private static void printArray(int[] arr) {
        if (arr.length > 10) {
            for (int i = 0; i < 5; i++) {
                System.out.print(arr[i] + ", ");
            }
            System.out.print("..., ");
            for (int i = arr.length - 5; i < arr.length; i++) {
                System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
            }
        }
    }
}