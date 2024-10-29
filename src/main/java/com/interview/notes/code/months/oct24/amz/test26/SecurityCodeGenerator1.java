package com.interview.notes.code.months.oct24.amz.test26;

public class SecurityCodeGenerator1 {
    
    // Main solution method
    public static String findSecurityCode(String code, long k) {
        char[] codeArray = code.toCharArray();
        boolean changed = true;
        long actualChanges = 0;
        
        // Continue until no more changes possible or k changes reached
        while (changed && actualChanges < k) {
            changed = false;
            // Check each adjacent pair
            for (int i = 0; i < codeArray.length - 1; i++) {
                // If swapping makes string lexicographically larger
                if (codeArray[i] < codeArray[i + 1]) {
                    char temp = codeArray[i];
                    codeArray[i] = codeArray[i + 1];
                    codeArray[i + 1] = temp;
                    changed = true;
                    actualChanges++;
                    break;
                }
            }
        }
        
        return new String(codeArray);
    }
    
    // Test helper method
    private static void testCase(String code, long k, String expected, int testNumber) {
        String result = findSecurityCode(code, k);
        boolean passed = result.equals(expected);
        System.out.println("Test Case " + testNumber + ": " + 
                          (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Input: code=" + code + ", k=" + k);
            System.out.println("  Expected: " + expected);
            System.out.println("  Got: " + result);
        }
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Sample case 0
        testCase("0010", 5, "1000", 1);
        
        // Test Case 2: Sample case 1
        testCase("111", 2, "111", 2);
        
        // Test Case 3: Given example
        testCase("00100101", 4, "10010001", 3);
        
        // Test Case 4: Edge case - single character
        testCase("1", 1, "1", 4);
        
        // Test Case 5: Edge case - all zeros
        testCase("0000", 3, "0000", 5);
        
        // Test Case 6: Large string test
        StringBuilder largeCode = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeCode.append(i % 2);
        }
        long startTime = System.currentTimeMillis();
        String result = findSecurityCode(largeCode.toString(), 1000000);
        long endTime = System.currentTimeMillis();
        System.out.println("Test Case 6 (Large Input): " + 
                          ((endTime - startTime) < 5000 ? "PASS" : "FAIL") + 
                          " (Execution time: " + (endTime - startTime) + "ms)");
        
        // Test Case 7: Maximum k value test
        testCase("010", 1000000000000L, "100", 7);
        
        // Test Case 8: Alternating bits
        testCase("0101", 2, "1010", 8);
    }
}