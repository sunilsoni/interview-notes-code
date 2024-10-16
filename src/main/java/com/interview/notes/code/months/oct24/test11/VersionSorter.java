package com.interview.notes.code.months.oct24.test11;

import java.util.*;

public class VersionSorter {

    public static List<String> sortVersions(List<String> versions) {
        Collections.sort(versions, (v1, v2) -> {
            String[] parts1 = v1.split("\\.");
            String[] parts2 = v2.split("\\.");
            
            for (int i = 0; i < Math.min(parts1.length, parts2.length); i++) {
                int num1 = Integer.parseInt(parts1[i]);
                int num2 = Integer.parseInt(parts2[i]);
                if (num1 != num2) {
                    return Integer.compare(num1, num2);
                }
            }
            
            return Integer.compare(parts1.length, parts2.length);
        });
        
        return versions;
    }

    public static void main(String[] args) {
        runTestCases();
    }

    public static void runTestCases() {
        // Test case 1: Given example
        List<String> input1 = Arrays.asList("3.1.10", "3.2.4", "3.4.2", "3.1.20", "3.2.2");
        List<String> expected1 = Arrays.asList("3.1.10", "3.1.20", "3.2.2", "3.2.4", "3.4.2");
        testCase(input1, expected1, "Test Case 1");

        // Test case 2: Empty list
        List<String> input2 = new ArrayList<>();
        List<String> expected2 = new ArrayList<>();
        testCase(input2, expected2, "Test Case 2 (Empty List)");

        // Test case 3: Single element
        List<String> input3 = Arrays.asList("1.0.0");
        List<String> expected3 = Arrays.asList("1.0.0");
        testCase(input3, expected3, "Test Case 3 (Single Element)");

        // Test case 4: Large input
        List<String> input4 = generateLargeInput(10000);
        List<String> expected4 = new ArrayList<>(input4);
        Collections.sort(expected4);
        testCase(input4, expected4, "Test Case 4 (Large Input)");

        // Test case 5: Versions with different number of parts
        List<String> input5 = Arrays.asList("1.0", "1.0.0", "1.0.0.1", "1");
        List<String> expected5 = Arrays.asList("1", "1.0", "1.0.0", "1.0.0.1");
        testCase(input5, expected5, "Test Case 5 (Different Parts)");
    }

    private static void testCase(List<String> input, List<String> expected, String testName) {
        List<String> result = sortVersions(new ArrayList<>(input));
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + result);
        }
    }

    private static List<String> generateLargeInput(int size) {
        List<String> input = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            StringBuilder version = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                if (j > 0) version.append(".");
                version.append(random.nextInt(10));
            }
            input.add(version.toString());
        }
        return input;
    }
}
