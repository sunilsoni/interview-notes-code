package com.interview.notes.code.year.y2025.may.meta.test2;

public class StringAddition {
    
    // Main method to solve and test the string addition
    public static String addStrings(String num1, String num2) {
        // StringBuilder to store result (more efficient than String for concatenation)
        StringBuilder result = new StringBuilder();
        
        // Get lengths of both strings
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;  // To store carry over value
        
        // Process digits from right to left until both strings are processed
        while (i >= 0 || j >= 0 || carry > 0) {
            // Get digits or use 0 if string end reached
            int digit1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j) - '0' : 0;
            
            // Calculate sum and new carry
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            
            // Insert current digit at beginning of result
            result.insert(0, sum % 10);
            
            // Move pointers
            i--;
            j--;
        }
        
        return result.toString();
    }
    
    // Test method with various cases
    public static void main(String[] args) {
        // Test case 1: Regular numbers
        test("123", "456", "579");
        
        // Test case 2: Different lengths
        test("1", "999", "1000");
        
        // Test case 3: Zero handling
        test("0", "0", "0");
        
        // Test case 4: Large numbers
        test("9999999999", "1", "10000000000");
        
        // Test case 5: Very large numbers
        test("123456789123456789", "987654321987654321", "1111111111111111110");
    }
    
    // Helper method to run tests
    private static void test(String num1, String num2, String expected) {
        String result = addStrings(num1, num2);
        boolean passed = result.equals(expected);
        
        System.out.println("Test Case:");
        System.out.println("Input: " + num1 + " + " + num2);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
}
