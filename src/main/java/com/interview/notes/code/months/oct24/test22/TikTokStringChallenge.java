package com.interview.notes.code.months.oct24.test22;

public class TikTokStringChallenge {
    
    public static int getMinTransformations(String caption) {
        if (caption == null || caption.length() < 2) {
            return 0;
        }
        
        char[] chars = caption.toCharArray();
        int minChanges = 0;
        
        // Handle first and last characters separately
        if (chars[0] != chars[1]) {
            minChanges++;
            chars[0] = chars[1]; // Make first char same as second
        }
        
        if (chars[chars.length-1] != chars[chars.length-2]) {
            minChanges++;
            chars[chars.length-1] = chars[chars.length-2]; // Make last char same as second-to-last
        }
        
        // Handle middle characters
        for (int i = 1; i < chars.length - 1; i++) {
            if (chars[i] != chars[i-1] && chars[i] != chars[i+1]) {
                minChanges++;
                chars[i] = chars[i-1]; // Make current char same as previous
            }
        }
        
        return minChanges;
    }
    
    // Test method
    private static void runTest(String caption, int expectedResult, String testName) {
        System.out.println("Running test: " + testName);
        try {
            int result = getMinTransformations(caption);
            boolean passed = result == expectedResult;
            System.out.println("Input: " + caption);
            System.out.println("Expected: " + expectedResult);
            System.out.println("Got: " + result);
            System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
            System.out.println("------------------------");
        } catch (Exception e) {
            System.out.println("Test FAILED with exception: " + e.getMessage());
            System.out.println("------------------------");
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Sample test case
        runTest("aca", 2, "Sample Test Case 1");
        
        // Test Case 2: Sample test case
        runTest("abab", 2, "Sample Test Case 2");
        
        // Test Case 3: Sample test case
        runTest("abcdef", 3, "Sample Test Case 3");
        
        // Test Case 4: Already valid string
        runTest("aabb", 0, "Already Valid String");
        
        // Test Case 5: All same characters
        runTest("aaaa", 0, "All Same Characters");
        
        // Test Case 6: Minimum length string
        runTest("ab", 1, "Minimum Length String");
        
        // Test Case 7: Large input with alternating characters
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append(i % 2 == 0 ? 'a' : 'b');
        }
        runTest(largeInput.toString(), 50000, "Large Input - Alternating");
        
        // Test Case 8: Large input with all different characters
        StringBuilder largeInput2 = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput2.append((char)('a' + (i % 26)));
        }
        runTest(largeInput2.toString(), 66666, "Large Input - Different");
        
        // Test Case 9: Edge cases
        runTest("z", 0, "Single Character");
        runTest("", 0, "Empty String");
        runTest(null, 0, "Null Input");
    }
}