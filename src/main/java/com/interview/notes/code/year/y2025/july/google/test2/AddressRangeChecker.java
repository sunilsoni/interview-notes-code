package com.interview.notes.code.year.y2025.july.google.test2;

import java.util.*;

public class AddressRangeChecker {
    // Class to represent a function with its address range
    static class Function {
        String name;     // Name of the function
        int start;      // Start address
        int end;        // End address
        
        Function(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }
    }

    // Method to check if address ranges are valid
    public static boolean isValidAddressRanges(List<Function> functions) {
        // Sort functions by start address to process in order
        functions.sort((a, b) -> a.start - b.start);
        
        // Stack to keep track of nested functions
        Stack<Function> stack = new Stack<>();
        
        for (Function curr : functions) {
            // If stack is empty, just push current function
            if (stack.isEmpty()) {
                stack.push(curr);
                continue;
            }
            
            Function parent = stack.peek();
            
            // Check if current function's range is within parent's range
            if (curr.start < parent.start || curr.end > parent.end) {
                return false;
            }
            
            // Remove any completed functions from stack
            while (!stack.isEmpty() && stack.peek().end < curr.end) {
                stack.pop();
            }
            
            stack.push(curr);
        }
        
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Valid nested functions
        List<Function> test1 = Arrays.asList(
            new Function("foo", 1, 100),
            new Function("bar", 25, 75),
            new Function("baz", 35, 45)
        );
        
        // Test Case 2: Invalid range - function extends beyond parent
        List<Function> test2 = Arrays.asList(
            new Function("foo", 1, 100),
            new Function("bar", 25, 75),
            new Function("baz", 35, 80)  // Invalid: ends after bar
        );
        
        // Test Case 3: Large data test
        List<Function> test3 = new ArrayList<>();
        test3.add(new Function("main", 0, 10000));
        for (int i = 0; i < 1000; i++) {
            test3.add(new Function("func" + i, i * 10, i * 10 + 8));
        }

        // Run tests and print results
        System.out.println("Test 1 (Valid nested functions): " + 
            (isValidAddressRanges(test1) ? "PASS" : "FAIL"));
        
        System.out.println("Test 2 (Invalid range): " + 
            (!isValidAddressRanges(test2) ? "PASS" : "FAIL"));
        
        System.out.println("Test 3 (Large data test): " + 
            (isValidAddressRanges(test3) ? "PASS" : "FAIL"));
    }
}
