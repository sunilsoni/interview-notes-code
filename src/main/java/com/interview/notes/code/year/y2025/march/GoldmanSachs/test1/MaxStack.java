package com.interview.notes.code.year.y2025.march.GoldmanSachs.test1;

import java.util.Stack;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

// Create a MaxStack class that has three functions: pop(), push(), getMax().
// Constraint: Every method should run in O(1) Efficiency.

// Eg.
// class MaxStack{
//    // int pop() - implement pop function
//    ..
//    // void push() - implement push function
//    ..
//    // int getMax() - implement getMax()
// }

// # Class usage
// MaxStack mystack = new MaxStack();
// mystack.push(3);
// mystack.push(2);
// mystack.push(4);

// mystack.getMax() // returns 4
// mystack.pop() // returns 4
// mystack.getMax() // returns 3
// mystack.pop() // returns 2
class MaxStack {
    // Main stack to store all elements
    private Stack<Integer> mainStack;
    
    // Auxiliary stack to track maximum values
    private Stack<Integer> maxStack;
    
    public MaxStack() {
        // Initialize both stacks
        mainStack = new Stack<>();
        maxStack = new Stack<>();
    }
    
    // Push element onto the stack
    public void push(int x) {
        // Always push to the main stack
        mainStack.push(x);
        
        // For max stack, we only push if it's empty or the new value is >= current max
        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        }
    }
    
    // Remove and return the top element
    public int pop() {
        if (mainStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        
        // Get the top element from main stack
        int popped = mainStack.pop();
        
        // If the popped element is the current maximum, remove from max stack too
        if (popped == maxStack.peek()) {
            maxStack.pop();
        }
        
        return popped;
    }
    
    // Return the maximum element in the stack
    public int getMax() {
        if (maxStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        
        // Simply return the top of max stack
        return maxStack.peek();
    }
}

class Solution {
    public static void main(String[] args) {
        // Test basic functionality
        System.out.println("Test Case 1: Basic operations");
        MaxStack stack1 = new MaxStack();
        stack1.push(3);
        stack1.push(2);
        stack1.push(4);
        
        boolean test1Pass = stack1.getMax() == 4;
        System.out.println("getMax() returns 4: " + (test1Pass ? "PASS" : "FAIL"));
        
        boolean test2Pass = stack1.pop() == 4;
        System.out.println("pop() returns 4: " + (test2Pass ? "PASS" : "FAIL"));
        
        boolean test3Pass = stack1.getMax() == 3;
        System.out.println("getMax() returns 3: " + (test3Pass ? "PASS" : "FAIL"));
        
        boolean test4Pass = stack1.pop() == 2;
        System.out.println("pop() returns 2: " + (test4Pass ? "PASS" : "FAIL"));
        
        // Test with duplicate values
        System.out.println("\nTest Case 2: Duplicate values");
        MaxStack stack2 = new MaxStack();
        stack2.push(5);
        stack2.push(5);
        stack2.push(3);
        
        boolean test5Pass = stack2.getMax() == 5;
        System.out.println("getMax() returns 5: " + (test5Pass ? "PASS" : "FAIL"));
        
        boolean test6Pass = stack2.pop() == 3;
        System.out.println("pop() returns 3: " + (test6Pass ? "PASS" : "FAIL"));
        
        boolean test7Pass = stack2.getMax() == 5;
        System.out.println("getMax() still returns 5: " + (test7Pass ? "PASS" : "FAIL"));
        
        boolean test8Pass = stack2.pop() == 5;
        System.out.println("pop() returns 5: " + (test8Pass ? "PASS" : "FAIL"));
        
        boolean test9Pass = stack2.getMax() == 5;
        System.out.println("getMax() still returns 5: " + (test9Pass ? "PASS" : "FAIL"));
        
        // Test with large data
        System.out.println("\nTest Case 3: Large data");
        MaxStack stack3 = new MaxStack();
        int max = Integer.MIN_VALUE;
        
        // Push 100,000 elements
        for (int i = 0; i < 100000; i++) {
            int value = (int)(Math.random() * 1000000);
            stack3.push(value);
            max = Math.max(max, value);
        }
        
        boolean test10Pass = stack3.getMax() == max;
        System.out.println("getMax() returns correct maximum: " + (test10Pass ? "PASS" : "FAIL"));
        
        // Test edge case - empty stack
        System.out.println("\nTest Case 4: Edge case - empty stack exception handling");
        MaxStack stack4 = new MaxStack();
        try {
            stack4.getMax();
            System.out.println("Empty stack getMax() test: FAIL (should throw exception)");
        } catch (IllegalStateException e) {
            System.out.println("Empty stack getMax() test: PASS (correctly threw exception)");
        }
        
        try {
            stack4.pop();
            System.out.println("Empty stack pop() test: FAIL (should throw exception)");
        } catch (IllegalStateException e) {
            System.out.println("Empty stack pop() test: PASS (correctly threw exception)");
        }
    }
}