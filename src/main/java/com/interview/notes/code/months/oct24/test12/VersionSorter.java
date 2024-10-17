package com.interview.notes.code.months.oct24.test12;

import java.util.*;

/*
Sort Version of the application.

I/P   [“3.1.10”, “3.2.4”, “3.4.2”,”3.1.20”, “3.2.2”]
O/P   [“3.1.10”, ”,”3.1.20”, ,“3.2.2” ,“3.2.4”, “3.4.2 ]

 */
public class VersionSorter {

    public static void main(String[] args) {
        // List of version strings to be sorted
        List<String> versions = Arrays.asList("3.1.10", "3.2.4", "3.4.2", "3.1.20", "3.2.2");
        List<String> expectedOutput = Arrays.asList("3.1.10", "3.1.20", "3.2.2", "3.2.4", "3.4.2");

        // Sort and print the versions
        System.out.println("Original Versions: " + versions);
        List<String> sortedVersions = sortVersions(versions);
        System.out.println("Sorted Versions: " + sortedVersions);

        // Run test cases to verify the sorting logic
        runTests();
    }

    // Sort the versions using Java 8 features
    public static List<String> sortVersions(List<String> versions) {
        // Sorting the list of versions using a custom comparator
        versions.sort((v1, v2) -> {
            // Split the version strings by '.' to get individual parts
            String[] v1Parts = v1.split("\\.");
            String[] v2Parts = v2.split("\\.");

            // Find the maximum length between the two version parts
            int length = Math.max(v1Parts.length, v2Parts.length);

            // Compare each part of the version
            for (int i = 0; i < length; i++) {
                // If a part is missing, consider it as 0
                int v1Part = i < v1Parts.length ? Integer.parseInt(v1Parts[i]) : 0;
                int v2Part = i < v2Parts.length ? Integer.parseInt(v2Parts[i]) : 0;

                // Log the comparison of each part
                System.out.println("Comparing parts: v1Part=" + v1Part + " v2Part=" + v2Part);

                // If the parts are not equal, return the difference
                if (v1Part != v2Part) {
                    return v1Part - v2Part;
                }
            }
            // If all parts are equal, return 0
            return 0;
        });
        return versions;
    }

    // Run test cases to check if sorting is correct
    public static void runTests() {
        // Test case 1: General test case
        List<String> testCase1 = Arrays.asList("3.1.10", "3.2.4", "3.4.2", "3.1.20", "3.2.2");
        List<String> expected1 = Arrays.asList("3.1.10", "3.1.20", "3.2.2", "3.2.4", "3.4.2");
        System.out.println("Running Test Case 1...");
        assert checkPassFail(testCase1, expected1) : "Test Case 1 Failed";

        // Additional test cases for edge cases
        // Test case 2: Versions with varying number of parts
        List<String> testCase2 = Arrays.asList("1.0", "1.0.1", "1");
        List<String> expected2 = Arrays.asList("1", "1.0", "1.0.1");
        System.out.println("Running Test Case 2...");
        assert checkPassFail(testCase2, expected2) : "Test Case 2 Failed";

        // Test case 3: Versions with different lengths and leading zeros
        List<String> testCase3 = Arrays.asList("2.0", "2.0.0", "2.0.1", "2.1", "2");
        List<String> expected3 = Arrays.asList("2", "2.0", "2.0.0", "2.0.1", "2.1");
        System.out.println("Running Test Case 3...");
        assert checkPassFail(testCase3, expected3) : "Test Case 3 Failed";

        // Large data test case to verify performance
        List<String> largeDataTestCase = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeDataTestCase.add("1.0." + i);
        }
        // Expected output is the same list sorted numerically by the third part
        List<String> largeDataExpected = new ArrayList<>(largeDataTestCase);
        Collections.sort(largeDataExpected, Comparator.comparingInt(v -> Integer.parseInt(v.split("\\.")[2])));
        System.out.println("Running Large Data Test Case...");
        assert checkPassFail(largeDataTestCase, largeDataExpected) : "Large Data Test Case Failed";

        System.out.println("All test cases passed.");
    }

    // Helper method to check if the sorted output matches the expected output
    public static boolean checkPassFail(List<String> input, List<String> expected) {
        // Sort the input list and compare with the expected output
        System.out.println("Input Versions: " + input);
        List<String> sortedOutput = sortVersions(new ArrayList<>(input));
        System.out.println("Sorted Output: " + sortedOutput);
        System.out.println("Expected Output: " + expected);
        return sortedOutput.equals(expected);
    }
}
