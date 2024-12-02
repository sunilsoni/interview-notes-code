package com.interview.notes.code.year.y2024.oct24.test16;

public class Kangaroo {

    public static String kangaroo(int x1, int v1, int x2, int v2) {
        // If kangaroo 1 is behind and jumps slower or equal to kangaroo 2, they will never meet
        if (v1 <= v2) {
            return "NO";
        }
        // Check if the number of jumps 'n' is a whole number
        if ((x2 - x1) % (v1 - v2) == 0) {
            return "YES";
        }
        return "NO";
    }

    // Test method to check various test cases
    public static void testKangaroo() {
        int[][] testCases = {
                {0, 3, 4, 2},  // YES
                {0, 2, 5, 3},  // NO
                {14, 4, 98, 2}, // YES
                {21, 6, 47, 3}, // NO
                {0, 2, 0, 2},  // YES
                {43, 2, 70, 2} // NO
        };
        String[] expectedResults = {"YES", "NO", "YES", "NO", "YES", "NO"};

        boolean allPass = true;

        for (int i = 0; i < testCases.length; i++) {
            int x1 = testCases[i][0];
            int v1 = testCases[i][1];
            int x2 = testCases[i][2];
            int v2 = testCases[i][3];

            String result = kangaroo(x1, v1, x2, v2);
            String expected = expectedResults[i];

            if (result.equals(expected)) {
                System.out.println("Test case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + expected + ", Got: " + result);
                allPass = false;
            }
        }

        // Large data input test
        int x1 = 0;
        int v1 = 10000;
        int x2 = 1;
        int v2 = 1;

        String largeDataResult = kangaroo(x1, v1, x2, v2);
        if (largeDataResult.equals("YES")) {
            System.out.println("Large data test case: PASS");
        } else {
            System.out.println("Large data test case: FAIL");
            allPass = false;
        }

        if (allPass) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    public static void main(String[] args) {
        testKangaroo();
    }
}
