package com.interview.notes.code.months.oct24.test27;

public class RainwaterTankSolution {
    public static int solution(String S) {
        int n = S.length();
        int tankCount = 0;
        boolean[] hasTank = new boolean[n]; // Track positions where tanks are placed

        // Iterate through each character in the string
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == 'H') {
                // Check if the house is already served by a tank on the left
                if (i > 0 && hasTank[i - 1]) {
                    // If there is already a tank to the left, skip to the next house
                    continue;
                }
                // Try to place a tank to the right of the house if there is an empty plot
                if (i + 1 < n && S.charAt(i + 1) == '-') {
                    hasTank[i + 1] = true; // Place a tank to the right of the house
                    tankCount++; // Increment the tank count
                }
                // If no empty plot is available to the right, try to place a tank to the left
                else if (i > 0 && S.charAt(i - 1) == '-') {
                    hasTank[i - 1] = true; // Place a tank to the left of the house
                    tankCount++; // Increment the tank count
                }
                // If no adjacent empty plot is available, it is impossible to serve this house
                else {
                    return -1; // Return -1 indicating that it is not possible to place tanks for all houses
                }
            }
        }
        return tankCount; // Return the total number of tanks placed
    }

    public static int solution1(String S) {
        if (S == null || S.isEmpty()) {
            return 0;
        }

        int tanks = 0;
        boolean prevHouse = false;
        boolean currentHouseCovered = false;

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == 'H') {
                if (!currentHouseCovered) {
                    if (i > 0 && S.charAt(i - 1) == '-') {
                        tanks++;
                        currentHouseCovered = true;
                    } else if (i == S.length() - 1 || S.charAt(i + 1) != '-') {
                        return -1; // No place for tank
                    }
                }
                prevHouse = true;
                currentHouseCovered = false;
            } else if (c == '-') {
                if (prevHouse && !currentHouseCovered) {
                    tanks++;
                    currentHouseCovered = true;
                }
                prevHouse = false;
            }
        }

        return tanks;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "-H-HH--",
                "H",
                "HH-HH",
                "-H-H-H-H-H",
                "",
                "_",
                "HHH-",
                "-HHH",
                "---HHH---",
                "-H-H-H-H",
                "H-H-H-H-H",
                "--H--H--",
                "H-H-H-H-H-H-H-H-H-H" // Large input
        };

        int[] expectedOutputs = {
                2, -1, -1, 3, 0, 0, -1, -1, -1, 2, 5, 2, 10
        };

        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i];
            int expected = expectedOutputs[i];
            int result = solution(input);

            boolean passed = result == expected;
            System.out.println("Test Case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expected);
            System.out.println("Result: " + result);
            System.out.println();
        }

        // Large data input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeInput.append(i % 2 == 0 ? "H-" : "-H");
        }
        long startTime = System.nanoTime();
        int largeResult = solution(largeInput.toString());
        long endTime = System.nanoTime();
        System.out.println("Large Input Test:");
        System.out.println("Input Length: " + largeInput.length());
        System.out.println("Result: " + largeResult);
        System.out.println("Execution Time: " + (endTime - startTime) / 1000000 + " ms");
    }
}
