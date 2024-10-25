package com.interview.notes.code.months.oct24.test17;

/*
You are choreographing a circus show with various animals. For one act, you are given two kangaroos on a number line ready to jump in the positive direction (i.e, toward positive infinity).
• The first kangaroo starts at location z1 and moves at a rate of ul meters per jump.
• The second kangaroo starts at location 22 and moves at a rate of v2 meters per jump.
You have to figure out a way to get both kangaroos at the same location at the same time as part of the show. If it is possible, return YES, otherwise return NO.
Example
x1 = 2
v1 = 1
x2 = 1
02 = 2
After one jump, they are both at 2 = 3. (21 + v1 = 2 + 1. 22 + 02 = 1 + 2). so the answer is YES.
Function Description
Complete the function kangaroo in the editor below.
kangaroo has the following parameter(s):
• int x1, int vl: starting position and jump distance for kangaroo 1
• int x2, int v2: starting position and jump distance for kangaroo 2
Returns
• string: either YES or NO
Input Format
A single line of four space-separated integers denoting the respective values of 21. v1. 22. and v2.
Constraints
• 0≤ x1 < 22 ≤ 10000
• 1 ≤ v1 ≤ 10000
• 1 ≤ 02 ≤ 10000
Sample Input 0
0342
 */
public class Kangaroo {

    /**
     * Determines whether two kangaroos will land on the same spot after making the same number of jumps.
     *
     * @param x1 Starting position of kangaroo 1
     * @param v1 Jump distance of kangaroo 1
     * @param x2 Starting position of kangaroo 2
     * @param v2 Jump distance of kangaroo 2
     * @return "YES" if they meet, "NO" otherwise
     */
    public static String kangaroo(int x1, int v1, int x2, int v2) {
        // Log initial input values
        System.out.println("kangaroo() called with x1=" + x1 + ", v1=" + v1 + ", x2=" + x2 + ", v2=" + v2);

        // If the velocities are equal
        if (v1 == v2) {
            // Log when velocities are equal
            System.out.println("Velocities are equal: v1=" + v1 + ", v2=" + v2);
            // If starting positions are the same, they meet at every jump
            if (x1 == x2) {
                System.out.println("Starting positions are the same: x1=" + x1 + ", x2=" + x2 + ". Returning YES.");
                return "YES";
            } else {
                System.out.println("Starting positions are different: x1=" + x1 + ", x2=" + x2 + ". Returning NO.");
                return "NO";
            }
        }

        // Calculate the difference in starting positions
        int deltaX = x2 - x1;
        // Calculate the difference in velocities
        int deltaV = v1 - v2;

        // Log calculated differences
        System.out.println("Calculated deltaX=" + deltaX + ", deltaV=" + deltaV);

        // Avoid division by zero and check if kangaroo 1 can catch up
        // If deltaV is 0 or the product of deltaV and deltaX is negative, kangaroo 1 cannot catch up
        if (deltaV == 0 || deltaV * deltaX < 0) {
            System.out.println("Kangaroo 1 cannot catch up. Returning NO.");
            return "NO";
        }

        // Check if the number of jumps 'n' is an integer
        // If deltaX is divisible by deltaV, then they will meet at the same spot
        if (deltaX % deltaV == 0) {
            System.out.println("Number of jumps is an integer. Returning YES.");
            return "YES";
        } else {
            System.out.println("Number of jumps is not an integer. Returning NO.");
            return "NO";
        }
    }

    /**
     * Main method to process test cases and output PASS/FAIL for each.
     */
    public static void main(String[] args) {
        // List of test cases: each test case is an array of inputs and the expected output
        String[][] testCases = {
                {"0", "3", "4", "2", "YES"},
                {"0", "2", "5", "3", "NO"},
                {"21", "6", "47", "3", "NO"},
                {"14", "4", "98", "2", "YES"},
                {"0", "3", "0", "3", "YES"},
                {"5", "2", "5", "2", "YES"},
                {"0", "7", "10", "7", "NO"},
                {"0", "5", "5", "3", "YES"},
                {"0", "10000", "1", "10000", "NO"},
                {"4523", "8092", "9419", "8076", "YES"},
                {"0", "0", "0", "0", "YES"} // Both kangaroos don't move
        };

        boolean allPassed = true;
        for (int i = 0; i < testCases.length; i++) {
            // Parse the input values for the test case
            int x1 = Integer.parseInt(testCases[i][0]);
            int v1 = Integer.parseInt(testCases[i][1]);
            int x2 = Integer.parseInt(testCases[i][2]);
            int v2 = Integer.parseInt(testCases[i][3]);
            String expected = testCases[i][4];

            // Log test case details
            System.out.println("\nRunning Test Case " + (i + 1) + ": x1=" + x1 + ", v1=" + v1 + ", x2=" + x2 + ", v2=" + v2 + ", expected=" + expected);

            // Call the kangaroo method to get the result
            String result = kangaroo(x1, v1, x2, v2);

            // Compare the result with the expected output
            if (!result.equals(expected)) {
                allPassed = false;
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Input: x1=" + x1 + ", v1=" + v1 + ", x2=" + x2 + ", v2=" + v2);
                System.out.println("Expected: " + expected + ", Got: " + result);
            } else {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            }
        }

        // Output the final result of all test cases
        if (allPassed) {
            System.out.println("\nAll test cases passed!");
        } else {
            System.out.println("\nSome test cases failed. Please review the failed test cases above.");
        }
    }
}
