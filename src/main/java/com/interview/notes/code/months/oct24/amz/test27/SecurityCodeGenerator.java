package com.interview.notes.code.months.oct24.amz.test27;

public class SecurityCodeGenerator {
    
    public static String findSecurityCode(String code, long k) {
        if (code.length() <= 1 || k == 0) return code;
        
        char[] arr = code.toCharArray();
        int n = arr.length;
        
        // Find the first cycle length
        int cycleLength = findCycleLength(arr.clone());
        
        // If k is larger than cycle length, we only need to perform k % cycleLength operations
        k = k > cycleLength ? cycleLength : k;
        
        // Perform actual swaps
        for (long i = 0; i < k; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    // Swap
                    char temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    break;
                }
            }
            if (!swapped) break;
        }
        
        return new String(arr);
    }
    
    // Helper method to find cycle length
    private static int findCycleLength(char[] arr) {
        int count = 0;
        int n = arr.length;
        boolean changed;
        
        do {
            changed = false;
            for (int j = 0; j < n - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    char temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    changed = true;
                    count++;
                    break;
                }
            }
        } while (changed);
        
        return count;
    }
    
    // Test helper
    private static void testCase(String code, long k, String expected, int testNumber) {
        long startTime = System.nanoTime();
        String result = findSecurityCode(code, k);
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        boolean passed = result.equals(expected);
        System.out.printf("Test Case %d: %s (%.2f ms)%n", 
                         testNumber, 
                         passed ? "PASS" : "FAIL",
                         executionTime);
        
        if (!passed) {
            System.out.println("  Input: code=" + code + ", k=" + k);
            System.out.println("  Expected: " + expected);
            System.out.println("  Got: " + result);
        }
    }
    
    public static void main(String[] args) {
        // Basic test cases
        testCase("0010", 5, "1000", 1);
        testCase("111", 2, "111", 2);
        testCase("00100101", 4, "10010001", 3);
        testCase("1", 1, "1", 4);
        testCase("0000", 3, "0000", 5);
        
        // Large input test
        System.out.println("\nTesting large inputs...");
        
        // Test 1: Large string with many operations
        StringBuilder largeCode1 = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
            largeCode1.append(i % 2);
        }
        long startTime = System.currentTimeMillis();
        findSecurityCode(largeCode1.toString(), 1_000_000);
        long endTime = System.currentTimeMillis();
        System.out.printf("Large Test 1 (1M chars, 1M ops): %.2f seconds%n", 
                         (endTime - startTime) / 1000.0);
        
        // Test 2: Maximum k value test
        testCase("010", 1_000_000_000_000L, "100", 6);
        
        // Test 3: Alternating bits with large k
        testCase("0101010101", 1_000_000L, "1111100000", 7);
        
        // Test 4: Random large string
        StringBuilder largeCode2 = new StringBuilder();
        for (int i = 0; i < 100_000; i++) {
            largeCode2.append((int)(Math.random() * 2));
        }
        startTime = System.currentTimeMillis();
        findSecurityCode(largeCode2.toString(), 1_000_000);
        endTime = System.currentTimeMillis();
        System.out.printf("Large Test 4 (100K random chars): %.2f seconds%n", 
                         (endTime - startTime) / 1000.0);
    }
}