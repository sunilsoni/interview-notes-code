package com.interview.notes.code.months.sept24.test6;

public class SnowpackCalculator {
    
    public static int computeSnowpack(int[] elevations) {
        if (elevations == null || elevations.length <= 2) {
            return 0;
        }
        
        int left = 0;
        int right = elevations.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int snowUnits = 0;
        
        while (left < right) {
            if (elevations[left] <= elevations[right]) {
                if (elevations[left] >= leftMax) {
                    leftMax = elevations[left];
                } else {
                    snowUnits += leftMax - elevations[left];
                }
                left++;
            } else {
                if (elevations[right] >= rightMax) {
                    rightMax = elevations[right];
                } else {
                    snowUnits += rightMax - elevations[right];
                }
                right--;
            }
        }
        
        return snowUnits;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[] test1 = {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0};
        System.out.println("Test 1: " + computeSnowpack(test1)); // Expected: 13
        
        int[] test2 = {1, 0, 2, 1, 3, 1, 2, 0, 1};
        System.out.println("Test 2: " + computeSnowpack(test2)); // Expected: 5
        
        int[] test3 = {4, 1, 1, 0, 2, 3};
        System.out.println("Test 3: " + computeSnowpack(test3)); // Expected: 5
        
        int[] test4 = {1, 2, 3, 4, 5};
        System.out.println("Test 4: " + computeSnowpack(test4)); // Expected: 0
        
        int[] test5 = {};
        System.out.println("Test 5: " + computeSnowpack(test5)); // Expected: 0
    }
    
    public static boolean doTestsPass() {
        boolean allTestsPassed = true;
        
        // Test cases
        int[][] testCases = {
            {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0},
            {1, 0, 2, 1, 3, 1, 2, 0, 1},
            {4, 1, 1, 0, 2, 3},
            {1, 2, 3, 4, 5},
            {}
        };
        
        int[] expectedResults = {13, 5, 5, 0, 0};
        
        for (int i = 0; i < testCases.length; i++) {
            int result = computeSnowpack(testCases[i]);
            if (result != expectedResults[i]) {
                System.out.println("Test case " + (i + 1) + " failed. Expected: " + expectedResults[i] + ", Got: " + result);
                allTestsPassed = false;
            }
        }
        
        return allTestsPassed;
    }
}
