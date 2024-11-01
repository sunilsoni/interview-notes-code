package com.interview.notes.code.months.oct24.tst24;

public class RainwaterTankSolution {

    public static int solution(String S) {
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
