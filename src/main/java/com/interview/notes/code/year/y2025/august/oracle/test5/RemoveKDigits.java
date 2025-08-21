package com.interview.notes.code.year.y2025.august.oracle.test5;

import java.util.*;
import java.util.stream.*;

public class RemoveKDigits {
    
    // Main method to solve the problem of removing k digits to get smallest number
    public static String removeKdigits(String num, int k) {
        // If k equals string length, return "0" as removing all digits
        if (k == num.length()) return "0";
        
        // Using Stack to track digits and maintain ascending order
        Stack<Character> stack = new Stack<>();
        
        // Process each digit in the input number
        for (char digit : num.toCharArray()) {
            // Remove digits while k > 0 and current digit is smaller than stack top
            while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
                stack.pop(); // Remove larger digit
                k--; // Decrease remaining removals
            }
            stack.push(digit); // Add current digit to stack
        }
        
        // Remove remaining k digits from end if needed
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        // Build result string using StringBuilder for efficiency
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        
        // Remove leading zeros using Stream API
        String finalResult = result.toString();
        finalResult = finalResult.replaceFirst("^0+(?!$)", "");
        
        // Return "0" if empty string, else return result
        return finalResult.isEmpty() ? "0" : finalResult;
    }
    
    // Test method to verify solution with various test cases
    public static void main(String[] args) {
        // Test case structure: input number, k value, expected output
        Object[][] testCases = {
            {"1432219", 3, "1219"},
            {"2222", 2, "22"},
            {"10200", 1, "200"},
            {"10", 2, "0"},
            {"112", 1, "11"},
            {"1234567890", 9, "0"},
            {"123456789", 5, "1234"}
        };
        
        // Process each test case and verify results
        for (Object[] test : testCases) {
            String num = (String) test[0];
            int k = (int) test[1];
            String expected = (String) test[2];
            String result = removeKdigits(num, k);
            
            // Print test results with PASS/FAIL indication
            System.out.printf("Input: num=%s, k=%d\n", num, k);
            System.out.printf("Expected: %s, Got: %s\n", expected, result);
            System.out.printf("Test: %s\n\n", 
                expected.equals(result) ? "PASS" : "FAIL");
        }
        
        // Large data test case
        StringBuilder largeNum = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeNum.append(i % 10);
        }
        long startTime = System.currentTimeMillis();
        String largeResult = removeKdigits(largeNum.toString(), 5000);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Large Data Test:");
        System.out.printf("Time taken: %d ms\n", endTime - startTime);
        System.out.println("Result length: " + largeResult.length());
    }
}
