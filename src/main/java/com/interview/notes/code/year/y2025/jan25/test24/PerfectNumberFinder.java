package com.interview.notes.code.year.y2025.jan25.test24;

/*

Find "Perfect Numbers" between 1 and  given N . where Perfect Numbers sum of its divisor

6 is a perfect number because its divisors
are 1, 2, and 3, and 1 + 2 + 3 = 6.
A "Perfect Number" is a positive integer that is equal to the sum of its proper divisors (excluding the number itself). For example, 6 is a perfect number because its divisors
are 1, 2, and 3, and 1 + 2 + 3 = 6.

 */
public class PerfectNumberFinder {

    /**
     * Checks if a given number is a perfect number.
     *
     * @param number The number to check.
     * @return True if the number is perfect, else false.
     */
    public static boolean isPerfectNumber(int number) {
        if (number < 2) {
            return false;
        }

        int sum = 1; // 1 is always a proper divisor

        // Find divisors up to the square root of the number
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                sum += i;
                int correspondingDivisor = number / i;
                if (correspondingDivisor != i) { // Avoid adding the square root twice
                    sum += correspondingDivisor;
                }
            }
        }

        return sum == number;
    }

    /**
     * Finds all perfect numbers between 1 and N.
     *
     * @param N The upper limit.
     * @return An array of perfect numbers.
     */
    public static int[] findPerfectNumbers(int N) {
        // Since the number of perfect numbers is small, we can use a dynamic list
        java.util.List<Integer> perfectNumbers = new java.util.ArrayList<>();

        for (int i = 2; i <= N; i++) {
            if (isPerfectNumber(i)) {
                perfectNumbers.add(i);
            }
        }

        // Convert the list to an array
        return perfectNumbers.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Main method for testing the PerfectNumberFinder.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Define test cases as arrays where the first element is N
        // and the second element is the expected list of perfect numbers up to N
        Object[][] testCases = {
                {5, new int[]{}},
                {6, new int[]{6}},
                {28, new int[]{6, 28}},
                {496, new int[]{6, 28, 496}},
                {8128, new int[]{6, 28, 496, 8128}},
                {33550336, new int[]{6, 28, 496, 8128, 33550336}}, // Large N
                {1, new int[]{}}, // Edge case: N < 6
                {0, new int[]{}}, // Edge case: N <= 0
                {-10, new int[]{}} // Edge case: Negative N
        };

        boolean allTestsPassed = true;

        for (int i = 0; i < testCases.length; i++) {
            int N = (int) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];
            int[] result = findPerfectNumbers(N);

            if (java.util.Arrays.equals(result, expected)) {
                System.out.println("Test Case " + (i + 1) + " PASSED");
            } else {
                System.out.println("Test Case " + (i + 1) + " FAILED");
                System.out.println("Input N: " + N);
                System.out.println("Expected: " + java.util.Arrays.toString(expected));
                System.out.println("Got: " + java.util.Arrays.toString(result));
                allTestsPassed = false;
            }
        }

        // Additional Test: Large N
        int largeN = 100000;
        int[] largeTestResult = findPerfectNumbers(largeN);
        System.out.println("Perfect numbers up to " + largeN + ": " + java.util.Arrays.toString(largeTestResult));

        if (allTestsPassed) {
            System.out.println("All test cases PASSED.");
        } else {
            System.out.println("Some test cases FAILED.");
        }
    }
}
