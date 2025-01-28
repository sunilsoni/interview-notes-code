package com.interview.notes.code.year.y2025.jan24.oracle.test1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ErrorLogTester {

    /**
     * Reads a file and returns the first n lines that start with "ERROR".
     *
     * @param fileName The name of the file to read.
     * @param n        The number of error lines to return.
     * @return A list of error lines.
     */
    public static List<String> returnNFirstErrors(String fileName, int n) {
        List<String> errorMessages = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null && errorMessages.size() < n) {
                if (line.startsWith("ERROR")) {
                    errorMessages.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return errorMessages;
    }

    /**
     * Helper method to create a test file with specified content.
     *
     * @param fileName The name of the file to create.
     * @param lines    The lines to write into the file.
     */
    private static void createTestFile(String fileName, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compares two lists of strings for equality.
     *
     * @param expected The expected list.
     * @param actual   The actual list.
     * @return True if both lists are equal, false otherwise.
     */
    private static boolean compareLists(List<String> expected, List<String> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Example provided
        String file1 = "test1.log";
        List<String> lines1 = Arrays.asList(
                "INFO: System initialized",
                "ERROR: Disk not found",
                "WARNING: Low memory",
                "ERROR: Out of memory",
                "ERROR: Network unreachable"
        );
        createTestFile(file1, lines1);
        List<String> expected1 = Arrays.asList(
                "ERROR: Disk not found",
                "ERROR: Out of memory"
        );
        testCases.add(new TestCase(file1, 2, expected1));

        // Test Case 2: No ERROR lines
        String file2 = "test2.log";
        List<String> lines2 = Arrays.asList(
                "INFO: System running",
                "WARNING: High CPU usage",
                "INFO: User logged in"
        );
        createTestFile(file2, lines2);
        List<String> expected2 = Collections.emptyList();
        testCases.add(new TestCase(file2, 3, expected2));

        // Test Case 3: All lines are ERROR
        String file3 = "test3.log";
        List<String> lines3 = Arrays.asList(
                "ERROR: Failure A",
                "ERROR: Failure B",
                "ERROR: Failure C"
        );
        createTestFile(file3, lines3);
        List<String> expected3 = Arrays.asList(
                "ERROR: Failure A",
                "ERROR: Failure B",
                "ERROR: Failure C"
        );
        testCases.add(new TestCase(file3, 5, expected3));

        // Test Case 4: Different casing and leading spaces
        String file4 = "test4.log";
        List<String> lines4 = Arrays.asList(
                "error: lowercase error",
                "  ERROR: Leading spaces",
                "Error: Mixed case",
                "ERROR: Proper error"
        );
        createTestFile(file4, lines4);
        List<String> expected4 = Arrays.asList(
                "  ERROR: Leading spaces",
                "ERROR: Proper error"
        );
        testCases.add(new TestCase(file4, 2, expected4));

        // Test Case 5: Large file
        String file5 = "test5.log";
        List<String> lines5 = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            if (i % 1000 == 0) {
                lines5.add("ERROR: Error number " + i);
            } else {
                lines5.add("INFO: Info number " + i);
            }
        }
        createTestFile(file5, lines5);
        List<String> expected5 = Arrays.asList(
                "ERROR: Error number 1000",
                "ERROR: Error number 2000",
                "ERROR: Error number 3000",
                "ERROR: Error number 4000",
                "ERROR: Error number 5000"
        );
        testCases.add(new TestCase(file5, 5, expected5));

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<String> result = returnNFirstErrors(tc.fileName, tc.n);
            boolean pass = compareLists(tc.expectedOutput, result);
            System.out.println("Test Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("Expected: " + tc.expectedOutput);
                System.out.println("Actual:   " + result);
            }
        }

        // Cleanup test files
        for (TestCase tc : testCases) {
            File f = new File(tc.fileName);
            if (f.exists()) {
                f.delete();
            }
        }
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        String fileName;
        int n;
        List<String> expectedOutput;

        TestCase(String fileName, int n, List<String> expectedOutput) {
            this.fileName = fileName;
            this.n = n;
            this.expectedOutput = expectedOutput;
        }
    }
}
