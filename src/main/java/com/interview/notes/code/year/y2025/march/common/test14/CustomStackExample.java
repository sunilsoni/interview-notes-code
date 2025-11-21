package com.interview.notes.code.year.y2025.march.common.test14;

// Main class containing the entry point and test methods
public class CustomStackExample {

    // Main method: program execution starts here
    public static void main(String[] args) {
        // Run all defined tests and output pass/fail results
        runTests();
    }

    // Method to run various test cases for the CustomStack
    public static void runTests() {
        // --------------------------
        // Test 1: Basic push, peek, and pop operations
        // --------------------------
        CustomStack<Integer> stack = new CustomStack<>(); // Create a new stack for Integer values
        boolean test1 = true; // Initialize flag for test pass/fail status

        try {
            stack.push(10); // Push element 10 onto the stack
            stack.push(20); // Push element 20 onto the stack

            // Check if the top element is 20 (peek returns the last pushed element)
            if (stack.peek() != 20) {
                test1 = false; // Fail test if peek doesn't return 20
            }

            // Check if pop returns 20 and removes it
            if (stack.pop() != 20) {
                test1 = false; // Fail test if pop doesn't return 20
            }

            // Next pop should return 10, the previous element
            if (stack.pop() != 10) {
                test1 = false; // Fail test if pop doesn't return 10
            }

            // After removing all elements, the stack should be empty
            if (!stack.isEmpty()) {
                test1 = false; // Fail test if stack is not empty
            }
        } catch (Exception e) {
            test1 = false; // Fail test if any unexpected exception occurs
        }
        System.out.println("Test 1 (Basic operations): " + (test1 ? "PASS" : "FAIL"));

        // --------------------------
        // Test 2: Edge case - Pop from an empty stack should throw an exception
        // --------------------------
        boolean test2 = false; // Initialize flag; expect an exception here
        try {
            // Attempt to pop from an empty stack, which should throw EmptyStackException
            stack.pop();
        } catch (Exception e) {
            test2 = true; // If exception is caught, then the test passes
        }
        System.out.println("Test 2 (Pop empty stack): " + (test2 ? "PASS" : "FAIL"));

        // --------------------------
        // Test 3: Large data input test
        // --------------------------
        boolean test3 = true; // Initialize flag for test status
        CustomStack<Integer> largeStack = new CustomStack<>(); // New stack for large data test
        int largeSize = 1000000; // Define a large number of elements (one million)

        try {
            // Use Java 8 IntStream to efficiently push a large range of values onto the stack
            java.util.stream.IntStream.range(0, largeSize).forEach(largeStack::push);

            // Pop the top element; expected value is largeSize - 1
            if (largeStack.pop() != largeSize - 1) {
                test3 = false; // Fail test if the popped value is not as expected
            }
        } catch (Exception e) {
            test3 = false; // Fail test if any exception occurs during large data operations
        }
        System.out.println("Test 3 (Large data input): " + (test3 ? "PASS" : "FAIL"));
    }
}

// --------------------------
// CustomStack class: A generic stack implementation using an ArrayList
// --------------------------
class CustomStack<T> {
    // Internal storage for the stack elements
    private final java.util.ArrayList<T> elements;

    // Constructor: Initializes the internal storage
    public CustomStack() {
        elements = new java.util.ArrayList<>(); // Create a new ArrayList for storing elements
    }

    // Method to add an element to the top of the stack
    public void push(T item) {
        elements.add(item); // Add the new item at the end of the list (top of the stack)
    }

    // Method to remove and return the top element of the stack
    public T pop() {
        if (isEmpty()) {
            // If the stack is empty, throw an exception to indicate underflow
            throw new java.util.EmptyStackException();
        }
        // Remove and return the last element of the list (LIFO behavior)
        return elements.remove(elements.size() - 1);
    }

    // Method to return the top element without removing it
    public T peek() {
        if (isEmpty()) {
            // If the stack is empty, throw an exception
            throw new java.util.EmptyStackException();
        }
        // Return the last element of the list without removing it
        return elements.get(elements.size() - 1);
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return elements.isEmpty(); // Delegate to the ArrayList's isEmpty method
    }

    // Method to return the current size of the stack
    public int size() {
        return elements.size(); // Return the number of elements in the list
    }

    // Method to return a Java 8 stream of the stack elements (for demonstration or further processing)
    public java.util.stream.Stream<T> stream() {
        return elements.stream(); // Convert the ArrayList to a Stream
    }
}