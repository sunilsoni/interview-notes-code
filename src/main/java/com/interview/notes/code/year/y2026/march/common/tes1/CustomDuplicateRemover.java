package com.interview.notes.code.year.y2026.march.common.tes1;

public class CustomDuplicateRemover { // Declares the main public class for our custom logic program.

    // Custom method to remove duplicates manually without existing Java library methods.
    public static int[] removeDuplicates(int[] input) { // Declares a method taking an int array and returning a new unique int array.
        if (input == null || input.length == 0) { // Checks if the array is null or empty to prevent crash errors.
            return new int[0]; // Returns a new empty array because there is no data to process.
        } // Closes the null/empty check if-block.
        
        int uniqueCount = 1; // Initializes a counter at 1 because the very first element in an array is always unique.
        
        for (int i = 1; i < input.length; i++) { // Starts a loop from the second element (index 1) to the end of the array.
            if (input[i] != input[i - 1]) { // Checks if the current element is different from the element right before it.
                input[uniqueCount] = input[i]; // If it is different, we move this new unique element to the front of the array.
                uniqueCount++; // We increase our count of unique items found so far.
            } // Closes the if-block that checks for differences.
        } // Closes the loop iterating through the array.
        
        int[] result = new int[uniqueCount]; // Creates a brand new, clean array sized exactly to the number of unique elements we counted.

        // Starts a short loop to copy our gathered unique elements into the final result array.
        // Copies the element from the front of our modified original array to the new result array.
        // Closes the copying loop.
        System.arraycopy(input, 0, result, 0, uniqueCount);
        
        return result; // Returns the final array containing strictly the unique numbers.
    } // Closes the removeDuplicates method.

    // Custom method to check if two arrays are identical, avoiding the existing Arrays.equals() method.
    public static boolean areArraysEqual(int[] arr1, int[] arr2) { // Declares a method that takes two arrays and returns true or false.
        if (arr1.length != arr2.length) { // Checks if the arrays have different lengths immediately.
            return false; // Returns false because arrays of different lengths can never be equal.
        } // Closes the length check if-block.
        
        for (int i = 0; i < arr1.length; i++) { // Starts a loop to check each element one by one.
            if (arr1[i] != arr2[i]) { // Compares the exact elements at the exact same position in both arrays.
                return false; // Returns false immediately if even a single mismatch is found.
            } // Closes the element comparison if-block.
        } // Closes the checking loop.
        
        return true; // Returns true because the loop finished, meaning all elements matched perfectly.
    } // Closes the areArraysEqual method.

    // Main method for testing all cases.
    public static void main(String[] args) { // The main method serves as the entry point to execute our tests.
        record TestCase(int[] input, int[] expected, String name) {} // Uses Java 21 'record' to hold test data cleanly, minimizing code words.
        
        // Setup for handling Large Data inputs manually
        int[] largeInput = new int[1000000]; // Creates a massive array of one million elements for performance testing.
        int[] largeExpected = new int[5]; // Creates a smaller array to hold the expected 5 unique results from the large input.
        
        for(int i = 0; i < 1000000; i++) { // Loops one million times to fill our large test array manually.
            largeInput[i] = i / 200000; // Fills the array with sorted blocks of repeating numbers: 0s, 1s, 2s, 3s, and 4s.
        } // Closes the large input generation loop.
        
        for(int i = 0; i < 5; i++) { // Loops 5 times to manually fill our expected large test results.
            largeExpected[i] = i; // Sets the expected unique values: exactly 0, 1, 2, 3, and 4.
        } // Closes the large expected generation loop.

        // Initializing our test suite
        TestCase[] tests = { // Opens the array initialization block for our test data cases.
            new TestCase(new int[]{1, 1, 2, 3, 4, 4, 4, 5, 5, 7, 7}, new int[]{1, 2, 3, 4, 5, 7}, "Provided Case"), // Tests your specific array.
            new TestCase(new int[]{}, new int[]{}, "Empty Array Case"), // Edge case: tests how the logic handles an empty array.
            new TestCase(new int[]{9, 9, 9, 9}, new int[]{9}, "All Duplicates Case"), // Edge case: tests an array where every single number is identical.
            new TestCase(largeInput, largeExpected, "Large Data Case") // Performance case: tests the 1-million item generated array.
        }; // Closes the test array initialization.

        // Executing the tests
        for (TestCase test : tests) { // Starts a loop to run every individual test case.
            int[] result = removeDuplicates(test.input()); // Calls our custom logic method to process the data.
            boolean passed = areArraysEqual(result, test.expected()); // Checks if the result matches the expected output using our custom equality method.
            System.out.println(test.name() + " -> " + (passed ? "PASS" : "FAIL")); // Prints the test name and visually outputs PASS or FAIL.
        } // Closes the test execution loop.
        
    } // Closes the main method.
} // Closes the overall class block.