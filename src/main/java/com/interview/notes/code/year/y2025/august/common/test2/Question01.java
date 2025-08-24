package com.interview.notes.code.year.y2025.august.common.test2;

public class Question01 {
    public static void main(String[] args) {
        // Test cases to verify the isBalanced function
        runTestCases();
    }

    public static int isBalanced(int[] a) {
        // Edge case: empty array is considered balanced
        if (a == null || a.length == 0) {
            return 1;
        }

        // Iterate through each element in the array
        for (int i = 0; i < a.length; i++) {
            boolean foundMatch = false;
            // For each element, look for its negative counterpart
            for (int j = 0; j < a.length; j++) {
                // Check if current element's negative exists
                if (a[i] == -a[j]) {
                    foundMatch = true;
                    break;
                }
            }
            // If no matching negative found, array is not balanced
            if (!foundMatch) {
                return 0;
            }
        }
        // All elements have matching negatives
        return 1;
    }

    public static void runTestCases() {
        // Test case 1: Basic balanced array
        int[] test1 = {-2, 3, 2, -3};
        System.out.println("Test 1: " + (isBalanced(test1) == 1 ? "PASS" : "FAIL"));

        // Test case 2: Balanced array with duplicates
        int[] test2 = {-2, 2, 2, 2};
        System.out.println("Test 2: " + (isBalanced(test2) == 1 ? "PASS" : "FAIL"));

        // Test case 3: Unbalanced array
        int[] test3 = {-5, 2, -2};
        System.out.println("Test 3: " + (isBalanced(test3) == 0 ? "PASS" : "FAIL"));

        // Test case 4: Empty array
        int[] test4 = {};
        System.out.println("Test 4: " + (isBalanced(test4) == 1 ? "PASS" : "FAIL"));

        // Test case 5: Array with zeros
        int[] test5 = {0, 0, 0};
        System.out.println("Test 5: " + (isBalanced(test5) == 1 ? "PASS" : "FAIL"));

        // Test case 6: Large array test
        int[] test6 = new int[1000];
        for (int i = 0; i < 500; i++) {
            test6[i] = i;
            test6[i + 500] = -i;
        }
        System.out.println("Test 6 (Large array): " +
                (isBalanced(test6) == 1 ? "PASS" : "FAIL"));
    }
}
