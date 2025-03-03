package com.interview.notes.code.year.y2025.march.caspex.test2;

import java.util.*;
import java.util.stream.*;

public class BrothersGame {
    
    /**
     * Given a list of integers (only 0s and 1s), this method returns the maximum
     * number of 1s that can be obtained by inverting (flipping) the bits in exactly one contiguous subarray.
     *
     * The approach is to calculate the net gain for each possible flip.
     * For each element, assign a value: +1 for 0 (since 0 -> 1 gives a gain) and -1 for 1 (since 1 -> 0 gives a loss).
     * Then, using Kadane's algorithm, we find the maximum contiguous subarray sum (which is the maximum net gain).
     * The final answer is the original number of 1s plus this maximum gain.
     *
     * If the maximum gain is negative (which happens when the array is all 1s),
     * we choose to flip just one element, thus reducing the count by one.
     *
     * @param ar List of integers representing the binary array.
     * @return Maximum number of 1s achievable after one inversion.
     */
    public static int solve(List<Integer> ar) {
        int initialOnes = 0;
        int maxGain = Integer.MIN_VALUE;
        int currentGain = 0;
        
        // For each element in the array, calculate the "gain" if it is flipped.
        for (int bit : ar) {
            if (bit == 1) {
                initialOnes++;
            }
            // For a 0, flipping gives +1 gain; for a 1, flipping gives -1 loss.
            int value = (bit == 0) ? 1 : -1;
            // Update currentGain using Kadane's idea: either extend the current subarray or start fresh.
            currentGain = Math.max(value, currentGain + value);
            maxGain = Math.max(maxGain, currentGain);
        }
        
        // If maxGain is negative, it means every flip reduces the count (all ones case).
        // Since we have to perform one inversion, choose the minimal damage (flip a single element).
        if (maxGain < 0) {
            maxGain = -1;
        }
        
        return initialOnes + maxGain;
    }
    
    /**
     * The main method runs several test cases including the provided examples,
     * additional edge cases, and a large random test case.
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();
        
        // Provided test cases
        testCases.add(new TestCase(Arrays.asList(0, 1, 0, 0, 1), 4));
        testCases.add(new TestCase(Arrays.asList(1, 0, 0, 1, 0, 0), 5));
        
        // Additional test cases
        
        // Case: all ones - must flip one element, so result is (total ones - 1)
        testCases.add(new TestCase(Arrays.asList(1, 1, 1, 1), 3));
        
        // Case: all zeros - flipping the entire array gives all ones.
        testCases.add(new TestCase(Arrays.asList(0, 0, 0, 0), 4));
        
        // Case: single element 0.
        testCases.add(new TestCase(Arrays.asList(0), 1));
        
        // Case: single element 1.
        testCases.add(new TestCase(Arrays.asList(1), 0));
        
        // Additional mixed case:
        // Array: [0, 1, 1, 0, 1, 0, 0, 1, 1, 0]
        // Expected result calculation:
        //   Initial ones = 5.
        //   Maximum gain found using Kadane's algorithm is 2.
        //   So expected output = 5 + 2 = 7.
        testCases.add(new TestCase(Arrays.asList(0, 1, 1, 0, 1, 0, 0, 1, 1, 0), 7));
        
        // Large test case: Create a large array (e.g., 10,000 elements) of random 0s and 1s.
        List<Integer> largeTest = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            largeTest.add(rand.nextInt(2));
        }
        // For the large test case, we do not have an expected value beforehand.
        testCases.add(new TestCase(largeTest, -1));
        
        // Running test cases
        int passed = 0;
        int failed = 0;
        int testNum = 1;
        
        for (TestCase test : testCases) {
            int output = solve(test.input);
            // For large test case, just print the output.
            if (test.expected == -1) {
                System.out.println("Test Case " + testNum + " (Large Test Case): Output = " + output);
            } else {
                if (output == test.expected) {
                    System.out.println("Test Case " + testNum + ": PASS");
                    passed++;
                } else {
                    System.out.println("Test Case " + testNum + ": FAIL. Expected: " + test.expected + ", Got: " + output);
                    failed++;
                }
            }
            testNum++;
        }
        System.out.println("Total Passed: " + passed + ", Total Failed: " + failed);
    }
    
    // A simple helper class to encapsulate test case information.
    static class TestCase {
        List<Integer> input;
        int expected;
        
        public TestCase(List<Integer> input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}