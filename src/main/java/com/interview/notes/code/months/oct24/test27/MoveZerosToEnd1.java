package com.interview.notes.code.months.oct24.test27;

import java.util.*;
import java.util.stream.Collectors;

public class MoveZerosToEnd1 {

    /*
     * Implement method/function with name 'solve' below.
     * The function accepts the following as parameters:
     *   1. nums is of type List<Integer>.
     * Returns: List<Integer>.
     */
    public static List<Integer> solve(List<Integer> nums){
        if (nums == null || nums.isEmpty()) {
            return nums;
        }
        
        int lastNonZeroFoundAt = 0;
        
        // Iterate through the array
        for (int current = 0; current < nums.size(); current++) {
            if (nums.get(current) != 0) {
                // Swap elements at lastNonZeroFoundAt and current
                if (current != lastNonZeroFoundAt) {
                    int temp = nums.get(lastNonZeroFoundAt);
                    nums.set(lastNonZeroFoundAt, nums.get(current));
                    nums.set(current, temp);
                }
                lastNonZeroFoundAt++;
            }
        }
        
        return nums;
    }

    // Helper method to compare two lists
    private static boolean areListsEqual(List<Integer> list1, List<Integer> list2) {
        if (list1 == null && list2 == null)
            return true;
        if (list1 == null || list2 == null)
            return false;
        if (list1.size() != list2.size())
            return false;
        for(int i=0;i<list1.size();i++) {
            if(!list1.get(i).equals(list2.get(i)))
                return false;
        }
        return true;
    }

    // Method to print lists (for debugging purposes)
    private static String listToString(List<Integer> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example Test Case 1
        testCases.add(new TestCase(
            Arrays.asList(0, 1, 0, 3, 12),
            Arrays.asList(1, 3, 12, 0, 0),
            "Example Test Case 1"
        ));

        // Example Test Case 2
        testCases.add(new TestCase(
            Arrays.asList(0),
            Arrays.asList(0),
            "Example Test Case 2"
        ));

        // Additional Test Case 3: All zeros
        testCases.add(new TestCase(
            Arrays.asList(0, 0, 0, 0),
            Arrays.asList(0, 0, 0, 0),
            "All Zeros"
        ));

        // Additional Test Case 4: No zeros
        testCases.add(new TestCase(
            Arrays.asList(1, 2, 3, 4, 5),
            Arrays.asList(1, 2, 3, 4, 5),
            "No Zeros"
        ));

        // Additional Test Case 5: Mixed zeros and non-zeros
        testCases.add(new TestCase(
            Arrays.asList(4, 0, 5, 0, 3, 0, 1),
            Arrays.asList(4, 5, 3, 1, 0, 0, 0),
            "Mixed Zeros and Non-Zeros"
        ));

        // Additional Test Case 6: Single non-zero
        testCases.add(new TestCase(
            Arrays.asList(5),
            Arrays.asList(5),
            "Single Non-Zero"
        ));

        // Additional Test Case 7: Large Input
        List<Integer> largeInput = new ArrayList<>();
        List<Integer> largeExpected = new ArrayList<>();
        // Populate largeInput with 10000 elements: alternate between 0 and 1
        for(int i=0;i<10000;i++) {
            if(i % 2 == 0) {
                largeInput.add(0);
                largeExpected.add(0); // We'll reorder later
            }
            else {
                largeInput.add(1);
                largeExpected.add(1);
            }
        }
        // After moving zeros to the end, all 1's come first, followed by 0's
        List<Integer> largeExpectedReordered = new ArrayList<>();
        for(int i=0;i<10000;i++) {
            if(largeInput.get(i) != 0) {
                largeExpectedReordered.add(largeInput.get(i));
            }
        }
        for(int i=0;i<10000;i++) {
            if(largeInput.get(i) == 0) {
                largeExpectedReordered.add(0);
            }
        }
        testCases.add(new TestCase(
            largeInput,
            largeExpectedReordered,
            "Large Input (10000 elements)"
        ));

        // Additional Test Case 8: Alternating zeros and non-zeros
        testCases.add(new TestCase(
            Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1),
            Arrays.asList(1, 1, 1, 1, 0, 0, 0, 0),
            "Alternating Zeros and Non-Zeros"
        ));

        // Additional Test Case 9: All non-zeros
        testCases.add(new TestCase(
            Arrays.asList(2, -3, 5, 7, 9),
            Arrays.asList(2, -3, 5, 7, 9),
            "All Non-Zeros"
        ));

        // Additional Test Case 10: Zeros at the end
        testCases.add(new TestCase(
            Arrays.asList(1, 2, 3, 0, 0),
            Arrays.asList(1, 2, 3, 0, 0),
            "Zeros Already at the End"
        ));

        // Run all test cases
        int passed = 0;
        for(int i=0;i<testCases.size();i++) {
            TestCase tc = testCases.get(i);
            // Clone the input to avoid modifying the original test case
            List<Integer> inputClone = new ArrayList<>(tc.input);
            List<Integer> result = solve(inputClone);
            boolean isPass = areListsEqual(result, tc.expectedOutput);
            if(isPass) {
                System.out.println("Test Case " + (i+1) + " (" + tc.description + "): PASS");
                passed++;
            }
            else {
                System.out.println("Test Case " + (i+1) + " (" + tc.description + "): FAIL");
                System.out.println("  Expected: " + listToString(tc.expectedOutput));
                System.out.println("  Got     : " + listToString(result));
            }
        }

        System.out.println("\nTotal Passed: " + passed + " out of " + testCases.size());
    }

    // Inner class to represent a test case
    static class TestCase {
        List<Integer> input;
        List<Integer> expectedOutput;
        String description;

        TestCase(List<Integer> input, List<Integer> expectedOutput, String description) {
            this.input = input;
            this.expectedOutput = expectedOutput;
            this.description = description;
        }
    }
}
