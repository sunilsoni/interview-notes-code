package com.interview.notes.code.months.nov24.test12;

import java.util.*;

/*

No 2 consecutive repating characters:

Java input int sorted array rearrange sucha a way tat


        int[] arr = {1,1,2,2,2,3,4,4,4,4,5};
          //output: {1,1,2,2,3,4,4,5,2,4,4};
        //Staep1: {1,1,2,2,3,4,4,4,4,5,2}//put 2 at then end
        //Staep2: {1,1,2,2,3,4,4,4,5,2,4}//put 4 at then end
        //Staep3: {1,1,2,2,3,4,4,5,2,4,4}//put 4 at then end
        //Staep4: {1,1,2,2,3,4,4,5,2,4,4}//FInal output


        in this output it is sorted till 5 and then


java have an int array rearrange there is no more than 2 consecutive repeated numbers

input array is sroted

it shoud maintain original order
 */
public class RearrangeArray {

    /**
     * Rearranges the input sorted array so that no more than two identical numbers are consecutive.
     *
     * @param arr The input sorted integer array.
     * @return The rearranged array meeting the criteria.
     */
    public static int[] rearrangeArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        // Frequency map to count occurrences of each number
        Map<Integer, Integer> frequencyMap = new LinkedHashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // List to hold the result
        List<Integer> result = new ArrayList<>();

        // Queue to hold deferred elements
        Queue<Integer> deferred = new LinkedList<>();

        // Iterate through the frequency map
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            // Add up to two occurrences of the current number
            for (int i = 0; i < Math.min(2, count); i++) {
                result.add(num);
            }

            // If more than two occurrences, add the remaining to the deferred queue
            for (int i = 2; i < count; i++) {
                deferred.offer(num);
            }
        }

        // Process deferred elements
        while (!deferred.isEmpty()) {
            int num = deferred.poll();
            int size = result.size();

            // Check last two elements to avoid three consecutive duplicates
            if (size >= 2 && result.get(size - 1) == num && result.get(size - 2) == num) {
                // Find a position to insert the deferred number
                boolean inserted = false;
                for (int i = size - 1; i >= 0; i--) {
                    if (result.get(i) != num) {
                        result.add(i + 1, num);
                        inserted = true;
                        break;
                    }
                }
                // If unable to insert without violating the condition, append at the end
                if (!inserted) {
                    result.add(num);
                }
            } else {
                result.add(num);
            }
        }

        // Convert the result list back to an array
        int[] rearranged = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            rearranged[i] = result.get(i);
        }

        return rearranged;
    }

    /**
     * Main method to test the rearrangeArray function with various test cases.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Example provided
        testCases.add(new TestCase(
                new int[]{1, 1, 2, 2, 2, 3, 4, 4, 4, 4, 5},
                new int[]{1, 1, 2, 2, 3, 4, 4, 5, 2, 4, 4},
                "Example Test Case"
        ));

        // Test Case 2: All elements are the same
        testCases.add(new TestCase(
                new int[]{2, 2, 2, 2, 2},
                new int[]{2, 2, 2, 2, 2},
                "All Elements Same"
        ));

        // Test Case 3: No duplicates
        testCases.add(new TestCase(
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5},
                "No Duplicates"
        ));

        // Test Case 4: Multiple high-frequency elements
        testCases.add(new TestCase(
                new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4},
                new int[]{1, 1, 2, 2, 3, 3, 4, 4, 1, 2, 3, 4},
                "Multiple High-Frequency Elements"
        ));

        // Test Case 5: Empty array
        testCases.add(new TestCase(
                new int[]{},
                new int[]{},
                "Empty Array"
        ));

        // Test Case 6: Single element
        testCases.add(new TestCase(
                new int[]{5},
                new int[]{5},
                "Single Element"
        ));

        // Test Case 7: Large Input
        int[] largeInput = new int[100000];
        for (int i = 0; i < 100000; i++) {
            largeInput[i] = i % 100; // 100 unique numbers repeated 1000 times each
        }
        // For large input, we just ensure no more than two consecutive duplicates
        testCases.add(new TestCase(
                largeInput,
                null, // We'll handle validation separately
                "Large Input"
        ));

        // Execute test cases
        for (TestCase testCase : testCases) {
            boolean passed;
            if (testCase.expectedOutput != null) {
                int[] actualOutput = rearrangeArray(testCase.input);
                passed = Arrays.equals(actualOutput, testCase.expectedOutput);
            } else {
                // For large input, perform a different validation
                int[] actualOutput = rearrangeArray(testCase.input);
                passed = validateNoMoreThanTwoConsecutive(actualOutput);
            }
            System.out.println(testCase.description + ": " + (passed ? "PASS" : "FAIL"));
        }
    }

    /**
     * Validates that no more than two identical numbers are consecutive in the array.
     *
     * @param arr The array to validate.
     * @return True if the condition is met, else False.
     */
    private static boolean validateNoMoreThanTwoConsecutive(int[] arr) {
        if (arr.length <= 2) {
            return true;
        }
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] == arr[i - 1] && arr[i] == arr[i - 2]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper class to represent a test case.
     */
    static class TestCase {
        int[] input;
        int[] expectedOutput;
        String description;

        TestCase(int[] input, int[] expectedOutput, String description) {
            this.input = input;
            this.expectedOutput = expectedOutput;
            this.description = description;
        }
    }
}
