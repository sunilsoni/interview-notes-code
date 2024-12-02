package com.interview.notes.code.year.y2024.oct24.test30;

public class RainwaterTankSolution {

    public static int solution(String S) {
        if (S == null || S.isEmpty() || !S.contains("H")) {
            return 0;
        }

        char[] street = S.toCharArray();
        int n = street.length;

        // Check if first or last house has no possible tank placement
        if (street[0] == 'H' && (n == 1 || street[1] == 'H')) return -1;
        if (street[n - 1] == 'H' && (n == 1 || street[n - 2] == 'H')) return -1;

        int tanks = 0;

        for (int i = 0; i < n; i++) {
            if (street[i] == 'H') {
                // Check if current house already has tank
                if ((i > 0 && street[i - 1] == 'T') ||
                        (i < n - 1 && street[i + 1] == 'T')) {
                    continue;
                }

                // Try to place tank
                if (i > 0 && street[i - 1] == '-') {
                    street[i - 1] = 'T';
                    tanks++;
                } else if (i < n - 1 && street[i + 1] == '-') {
                    street[i + 1] = 'T';
                    tanks++;
                } else {
                    return -1; // No place for tank
                }
            }
        }

        return tanks;
    }

    public static void testCase(String input, int expected) {
        int result = solution(input);
        System.out.printf("Test Case: '%s'\n", input);
        System.out.printf("Expected: %d, Got: %d\n", expected, result);
        System.out.println(result == expected ? "PASS" : "FAIL");
        System.out.println("-------------------");
    }

    public static void main(String[] args) {
        // Basic test cases
        testCase("", 0);
        testCase("-", 0);
        testCase("H", -1);
        testCase("HHH-", -1);
        testCase("-HHH", -1);
        testCase("---HHH---", -1);
        testCase("-H-H-H-H", 2);

        // Additional test cases
        testCase("-H-HH--", 2);
        testCase("HH-HH", -1);
        testCase("-H-H-H-H-H", 3);

        // Large input test cases
        testCase("-H-H-H-H-H-H-H-H-H-H", 5);
        String largeInput = "-H-H".repeat(5000); // Testing with very large input
        long startTime = System.currentTimeMillis();
        int result = solution(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Test:");
        System.out.println("Input length: " + largeInput.length());
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}