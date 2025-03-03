package com.interview.notes.code.year.y2025.jan.oracle.test2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LogParser {
    public static List<String> returnNFirstErrors(String fileName, int n) {
        List<String> errors = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null && errors.size() < n) {
                if (line.startsWith("ERROR")) {
                    errors.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return errors;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create test file
        createTestFile("test.log");

        // Test cases
        testCase1();
        testCase2();
        testCase3();
        testCase4();
    }

    private static void createTestFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("INFO: System starting");
            writer.println("ERROR: Database connection failed");
            writer.println("WARNING: Low memory");
            writer.println("ERROR: Authentication error");
            writer.println("ERROR: Invalid input");
            writer.println("INFO: Process completed");
        } catch (IOException e) {
            System.err.println("Error creating test file: " + e.getMessage());
        }
    }

    private static void testCase1() {
        System.out.println("Test Case 1: Request 2 errors");
        List<String> result = returnNFirstErrors("test.log", 2);
        boolean passed = result.size() == 2 &&
                result.get(0).contains("Database connection failed") &&
                result.get(1).contains("Authentication error");
        System.out.println("Test Case 1: " + (passed ? "PASS" : "FAIL"));
    }

    private static void testCase2() {
        System.out.println("Test Case 2: Request 0 errors");
        List<String> result = returnNFirstErrors("test.log", 0);
        System.out.println("Test Case 2: " + (result.isEmpty() ? "PASS" : "FAIL"));
    }

    private static void testCase3() {
        System.out.println("Test Case 3: Request more errors than available");
        List<String> result = returnNFirstErrors("test.log", 10);
        System.out.println("Test Case 3: " + (result.size() == 3 ? "PASS" : "FAIL"));
    }

    private static void testCase4() {
        System.out.println("Test Case 4: Non-existent file");
        List<String> result = returnNFirstErrors("nonexistent.log", 1);
        System.out.println("Test Case 4: " + (result.isEmpty() ? "PASS" : "FAIL"));
    }
}
