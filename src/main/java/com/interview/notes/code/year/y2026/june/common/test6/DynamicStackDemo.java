package com.interview.notes.code.year.y2026.june.common.test6;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream; // Needed for Java Stream based large test

public class DynamicStackDemo { // Main class to run program

    static void check(String testName, Object expected, Object actual) { // Simple PASS/FAIL test method

        boolean pass = Objects.equals(expected, actual); // Compares expected and actual safely

        System.out.println(testName + " : " + (pass ? "PASS" : "FAIL") +
                " | Expected = " + expected + ", Actual = " + actual); // Prints test result
    }

    static void checkTrue(String testName, boolean condition) { // Test method for boolean conditions

        System.out.println(testName + " : " + (condition ? "PASS" : "FAIL")); // Prints PASS if condition true
    }

    public static void main(String[] args) { // Program starts from here

        DynamicStack<Integer> stack = new DynamicStack<>(); // Creates dynamic integer stack

        checkTrue("Test 1 - New stack should be empty", stack.isEmpty()); // Checks empty stack

        stack.push(10); // Pushes 10

        stack.push(20); // Pushes 20

        stack.push(30); // Pushes 30, capacity grows if needed

        check("Test 2 - Size after 3 pushes", 3, stack.size()); // Checks stack size

        check("Test 3 - Peek top value", 30, stack.peek()); // Top should be 30

        check("Test 4 - Pop first value", 30, stack.pop()); // LIFO, first pop should be 30

        check("Test 5 - Pop second value", 20, stack.pop()); // Next pop should be 20

        check("Test 6 - Pop third value", 10, stack.pop()); // Last pop should be 10

        checkTrue("Test 7 - Stack should be empty again", stack.isEmpty()); // Stack should be empty

        DynamicStack<String> stringStack = new DynamicStack<>(); // Creates dynamic string stack

        stringStack.push("Java"); // Pushes Java

        stringStack.push("Spring"); // Pushes Spring

        check("Test 8 - String stack pop", "Spring", stringStack.pop()); // Last pushed string should come first

        try { // Handles empty pop test

            stack.pop(); // Tries to pop from empty stack

            System.out.println("Test 9 - Empty pop exception : FAIL"); // Should not reach here

        } catch (NoSuchElementException e) { // Expected exception

            System.out.println("Test 9 - Empty pop exception : PASS"); // Confirms exception handling
        }

        DynamicStack<Integer> bigStack = new DynamicStack<>(); // Creates stack for large data test

        int n = 100_000; // Large input size

        IntStream.rangeClosed(1, n).forEach(bigStack::push); // Pushes 1 to 100000 using Stream API

        check("Test 10 - Large stack size", n, bigStack.size()); // Confirms all values pushed

        checkTrue("Test 11 - Capacity increased dynamically", bigStack.capacity() >= n); // Confirms dynamic capacity

        boolean orderCorrect = true; // Tracks whether pop order is correct

        for (int i = n; i >= 1; i--) { // Pops from 100000 down to 1

            if (bigStack.pop() != i) { // Checks LIFO order

                orderCorrect = false; // Marks failed if wrong value comes

                break; // Stops loop early
            }
        }

        checkTrue("Test 12 - Large stack LIFO order", orderCorrect); // Confirms correct stack behavior

        checkTrue("Test 13 - Large stack empty after pop", bigStack.isEmpty()); // Confirms stack empty after all pops
    }

    static class DynamicStack<T> { // Generic stack so it can store Integer, String, etc.

        private Object[] data = new Object[2]; // Initial small array, grows when needed

        private int size = 0; // Tracks current number of elements in stack

        public void push(T value) { // Adds new value on top of stack

            if (size == data.length) { // Checks if internal array is full

                data = Arrays.copyOf(data, data.length * 2); // Doubles capacity dynamically
            }

            data[size++] = value; // Stores value at current top and then increases size
        }

        @SuppressWarnings("unchecked") // Safe here because we only insert T type values

        public T pop() { // Removes and returns top value

            if (isEmpty()) { // Stack cannot pop if empty

                throw new NoSuchElementException("Stack is empty"); // Clear error message
            }

            T value = (T) data[--size]; // Decrease size first, then get top value

            data[size] = null; // Removes reference to avoid memory leak

            return value; // Returns removed top value
        }

        @SuppressWarnings("unchecked") // Safe because stack stores only T values

        public T peek() { // Returns top value without removing it

            if (isEmpty()) { // Stack cannot peek if empty

                throw new NoSuchElementException("Stack is empty"); // Clear error message
            }

            return (T) data[size - 1]; // Returns last inserted value
        }

        public boolean isEmpty() { // Checks whether stack has no elements

            return size == 0; // True when no elements are present
        }

        public int size() { // Returns current stack size

            return size; // Current number of elements
        }

        public int capacity() { // Returns current internal array capacity

            return data.length; // Useful to confirm dynamic growth
        }
    }
}