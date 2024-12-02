package com.interview.notes.code.year.y2024.nov24.test15;

public class LongestStringFinder {

    // Method to find longest string using Java 8 streams
    private static String findLongestString(String[] array) {
        if (array == null || array.length == 0) {
            return "";
        }

        return java.util.Arrays.stream(array)
                .filter(str -> str != null)  // Handle null values
                .max((s1, s2) -> Integer.compare(s1.length(), s2.length()))
                .orElse("");
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Normal case
        String[] test1 = {"Rajesh", "Kandi", "Abir", "Rajesh Abir Kandi"};
        testAndPrint("Test 1 - Normal case", test1, "Rajesh Abir Kandi");

        // Test Case 2: Empty array
        String[] test2 = {};
        testAndPrint("Test 2 - Empty array", test2, "");

        // Test Case 3: Array with null values
        String[] test3 = {"Rajesh", null, "Kandi", "Abir"};
        testAndPrint("Test 3 - Array with nulls", test3, "Rajesh");

        // Test Case 4: Array with duplicate longest strings
        String[] test4 = {"Hello World", "Hello World", "Hi"};
        testAndPrint("Test 4 - Duplicate longest", test4, "Hello World");

        // Test Case 5: Large data test
        String[] test5 = generateLargeInput(10000);
        testAndPrint("Test 5 - Large data", test5, "LongString9999");
    }

    // Helper method to test and print results
    private static void testAndPrint(String testName, String[] input, String expected) {
        long startTime = System.nanoTime();
        String result = findLongestString(input);
        long endTime = System.nanoTime();

        boolean passed = result.equals(expected);
        System.out.println(testName);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("Execution time: " + (endTime - startTime) / 1000000.0 + " ms");
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
        System.out.println();
    }

    // Helper method to generate large test data
    private static String[] generateLargeInput(int size) {
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = "LongString" + i;
        }
        return array;
    }
}
