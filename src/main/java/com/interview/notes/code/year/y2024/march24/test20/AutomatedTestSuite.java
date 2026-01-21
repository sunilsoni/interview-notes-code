package com.interview.notes.code.year.y2024.march24.test20;

import java.util.ArrayList;
import java.util.List;

public class AutomatedTestSuite {

    // Store all test results for summary
    private static final List<TestResult> allResults = new ArrayList<>();

    // ============================================================
    // THE FUNCTION WE ARE TESTING
    // ============================================================
    public static String removeComments(String input) {

        if (input == null) {
            return null;
        }

        if (input.length() == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int len = input.length();
        int i = 0;
        boolean inBlock = false;

        while (i < len) {
            char curr = input.charAt(i);
            char next = (i + 1 < len) ? input.charAt(i + 1) : ' ';

            if (inBlock) {
                if (curr == '*' && next == '/') {
                    inBlock = false;
                    i = i + 2;
                } else {
                    i = i + 1;
                }
            } else {
                if (curr == '/' && next == '/') {
                    while (i < len && input.charAt(i) != '\n') {
                        i = i + 1;
                    }
                } else if (curr == '/' && next == '*') {
                    inBlock = true;
                    i = i + 2;
                } else {
                    result.append(curr);
                    i = i + 1;
                }
            }
        }

        return result.toString();
    }

    // ============================================================
    // TEST RUNNER - Core testing method
    // ============================================================
    public static boolean runTest(String category, String name,
                                  String input, String expected) {

        // Record start time for performance tracking
        long startTime = System.currentTimeMillis();

        // Run the actual function
        String actual = removeComments(input);

        // Record end time
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Compare results
        boolean passed;
        if (expected == null) {
            passed = (actual == null);
        } else {
            passed = expected.equals(actual);
        }

        // Store result for later summary
        allResults.add(new TestResult(category, name, passed,
                input, expected, actual, duration));

        // Print immediate feedback
        String status = passed ? "[PASS]" : "[FAIL]";
        System.out.println(status + " " + category + " - " + name);

        // If failed, show details for debugging
        if (!passed) {
            System.out.println("       Input:    " + formatStr(input));
            System.out.println("       Expected: " + formatStr(expected));
            System.out.println("       Actual:   " + formatStr(actual));
        }

        return passed;
    }

    // Helper to format strings for display
    public static String formatStr(String s) {
        if (s == null) return "null";
        if (s.length() > 50) {
            return "\"" + s.substring(0, 50).replace("\n", "\\n") + "...\" (length: " + s.length() + ")";
        }
        return "\"" + s.replace("\n", "\\n") + "\"";
    }

    // CATEGORY 1: Happy Path Tests
    // These test normal, expected usage
    public static void runHappyPathTests() {

        System.out.println("\n========== HAPPY PATH TESTS ==========");

        // Test basic single-line comment removal
        runTest("HAPPY", "Basic single-line comment",
                "abc//def\nghi",
                "abc\nghi");

        // Test basic multi-line comment removal
        runTest("HAPPY", "Basic multi-line comment",
                "ab/*cd*/ef",
                "abef");

        // Test code with comment at end of line
        runTest("HAPPY", "Comment at end of line",
                "int x = 5; // initialize x\nint y = 10;",
                "int x = 5; \nint y = 10;");

        // Test multiple comments in same string
        runTest("HAPPY", "Multiple different comments",
                "a/*1*/b//2\nc/*3*/d",
                "ab\ncd");
    }

    // ============================================================
    // TEST CATEGORIES
    // ============================================================

    // CATEGORY 2: Edge Case Tests
    // These test boundary conditions
    public static void runEdgeCaseTests() {

        System.out.println("\n========== EDGE CASE TESTS ==========");

        // Comment at very beginning
        runTest("EDGE", "Comment at start - block",
                "/*start*/hello",
                "hello");

        // Comment at very end
        runTest("EDGE", "Comment at end - block",
                "hello/*end*/",
                "hello");

        // Comment at start - single line
        runTest("EDGE", "Comment at start - single",
                "//comment\nhello",
                "\nhello");

        // Adjacent comments (back to back)
        runTest("EDGE", "Adjacent block comments",
                "a/*x*//*y*/b",
                "ab");

        // Single slash (should NOT be treated as comment)
        runTest("EDGE", "Single slash - not comment",
                "a/b",
                "a/b");

        // Slash followed by space then star
        runTest("EDGE", "Slash space star - not comment",
                "a/ *b",
                "a/ *b");

        // Block comment spanning multiple lines
        runTest("EDGE", "Block comment multi-line",
                "a/*\ncomment\nhere\n*/b",
                "ab");

        // Single-line comment markers inside block
        runTest("EDGE", "// inside /* */",
                "a/* // */b",
                "ab");

        // Multiple stars inside block comment
        runTest("EDGE", "Stars inside block",
                "a/***/b",
                "ab");

        // Empty comment
        runTest("EDGE", "Empty block comment",
                "a/**/b",
                "ab");
    }

    // CATEGORY 3: Error/Invalid Input Tests
    // These test how function handles bad input
    public static void runErrorTests() {

        System.out.println("\n========== ERROR/INVALID TESTS ==========");

        // Null input
        runTest("ERROR", "Null input",
                null,
                null);

        // Empty string
        runTest("ERROR", "Empty string",
                "",
                "");

        // Only whitespace
        runTest("ERROR", "Only whitespace",
                "   \n\t  ",
                "   \n\t  ");

        // Only comment - nothing left
        runTest("ERROR", "Only single-line comment",
                "//everything",
                "");

        // Only block comment
        runTest("ERROR", "Only block comment",
                "/*everything*/",
                "");

        // No comments at all
        runTest("ERROR", "No comments present",
                "hello world",
                "hello world");
    }

    // CATEGORY 4: Large Data Tests
    // These test performance with big inputs
    public static void runLargeDataTests() {

        System.out.println("\n========== LARGE DATA TESTS ==========");

        // Test 1: Many single-line comments
        StringBuilder large1 = new StringBuilder();
        StringBuilder expected1 = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            large1.append("code" + i + "//comment\n");
            expected1.append("code" + i + "\n");
        }
        runTest("LARGE", "100K single-line comments",
                large1.toString(),
                expected1.toString());

        // Test 2: Many block comments
        StringBuilder large2 = new StringBuilder();
        StringBuilder expected2 = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            large2.append("a" + i + "/*block*/");
            expected2.append("a" + i);
        }
        runTest("LARGE", "50K block comments",
                large2.toString(),
                expected2.toString());

        // Test 3: Very long single line
        StringBuilder large3 = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            large3.append("x");
        }
        runTest("LARGE", "1M characters no comments",
                large3.toString(),
                large3.toString());

        // Test 4: Mixed large
        StringBuilder large4 = new StringBuilder();
        StringBuilder expected4 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            large4.append("line" + i + "/*b*/" + "//c\n");
            expected4.append("line" + i + "\n");
        }
        runTest("LARGE", "10K mixed comments",
                large4.toString(),
                expected4.toString());
    }

    // CATEGORY 5: Regression Tests
    // These test previously found bugs
    public static void runRegressionTests() {

        System.out.println("\n========== REGRESSION TESTS ==========");

        // Bug #1: Newline was being removed with single-line comment
        runTest("REGRESSION", "Bug#1: Preserve newline after //",
                "a//b\nc",
                "a\nc");

        // Bug #2: Unclosed block comment caused infinite loop
        // Note: This tests that function terminates properly
        runTest("REGRESSION", "Bug#2: Unclosed block at end",
                "abc/*unclosed",
                "abc");

        // Bug #3: */ appearing in code was mistakenly treated
        runTest("REGRESSION", "Bug#3: */ not in comment",
                "a*/b",  // */ without opening /*
                "a*/b");

        // Bug #4: Empty input caused null pointer
        runTest("REGRESSION", "Bug#4: Empty string handling",
                "",
                "");
    }

    // ============================================================
    // SUMMARY REPORT
    // ============================================================
    public static void printSummary() {

        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║              TEST SUITE SUMMARY REPORT               ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");

        // Count by category
        int totalPassed = 0;
        int totalFailed = 0;
        long totalTime = 0;

        // Group results by category
        String[] categories = {"HAPPY", "EDGE", "ERROR", "LARGE", "REGRESSION"};

        for (String cat : categories) {
            int catPassed = 0;
            int catFailed = 0;

            for (TestResult r : allResults) {
                if (r.category.equals(cat)) {
                    if (r.passed) {
                        catPassed++;
                        totalPassed++;
                    } else {
                        catFailed++;
                        totalFailed++;
                    }
                    totalTime += r.timeMs;
                }
            }

            int catTotal = catPassed + catFailed;
            if (catTotal > 0) {
                String status = (catFailed == 0) ? "✓" : "✗";
                System.out.printf("║  %s %-12s: %3d/%3d passed                      ║%n",
                        status, cat, catPassed, catTotal);
            }
        }

        System.out.println("╠══════════════════════════════════════════════════════╣");

        int total = totalPassed + totalFailed;
        double percentage = (total > 0) ? (totalPassed * 100.0 / total) : 0;

        System.out.printf("║  TOTAL: %d/%d tests passed (%.1f%%)                   ║%n",
                totalPassed, total, percentage);
        System.out.printf("║  TIME:  %d ms total execution                        ║%n",
                totalTime);
        System.out.println("╠══════════════════════════════════════════════════════╣");

        if (totalFailed == 0) {
            System.out.println("║  STATUS: ✓ ALL TESTS PASSED - Safe to deploy        ║");
        } else {
            System.out.println("║  STATUS: ✗ TESTS FAILED - Do NOT deploy             ║");
        }

        System.out.println("╚══════════════════════════════════════════════════════╝");

        // List failed tests if any
        if (totalFailed > 0) {
            System.out.println("\nFAILED TESTS:");
            for (TestResult r : allResults) {
                if (!r.passed) {
                    System.out.println("  - " + r.category + ": " + r.name);
                }
            }
        }
    }

    // ============================================================
    // MAIN - Run all tests
    // ============================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     AUTOMATED TEST SUITE - Remove Comments           ║");
        System.out.println("║     Running all test categories...                   ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        // Run all test categories in order
        runHappyPathTests();      // Normal usage
        runEdgeCaseTests();       // Boundary conditions
        runErrorTests();          // Invalid inputs
        runLargeDataTests();      // Performance tests
        runRegressionTests();     // Previously found bugs

        // Print final summary
        printSummary();
    }

    // Class to hold test result information
    static class TestResult {
        String category;      // Which category this test belongs to
        String name;          // Name of the test
        boolean passed;       // Did it pass?
        String input;         // What was the input?
        String expected;      // What did we expect?
        String actual;        // What did we get?
        long timeMs;          // How long did it take?

        TestResult(String category, String name, boolean passed,
                   String input, String expected, String actual, long timeMs) {
            this.category = category;
            this.name = name;
            this.passed = passed;
            this.input = input;
            this.expected = expected;
            this.actual = actual;
            this.timeMs = timeMs;
        }
    }
}