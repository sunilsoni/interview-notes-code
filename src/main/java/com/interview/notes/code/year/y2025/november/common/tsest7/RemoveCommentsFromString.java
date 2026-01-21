package com.interview.notes.code.year.y2025.november.common.tsest7;

public class RemoveCommentsFromString {

    // Main function to remove both single and multi-line comments
    public static String removeComments(String input) {

        // Check if input is null - return null for null input
        if (input == null) {
            return null;
        }

        // Check if input is empty - nothing to process
        if (input.length() == 0) {
            return "";
        }

        // StringBuilder is used to build result efficiently
        // String concatenation is slow, StringBuilder is fast
        StringBuilder result = new StringBuilder();

        // Store the length to avoid calling length() repeatedly
        int len = input.length();

        // Position tracker - tells us where we are in the string
        int i = 0;

        // Flag to know if we are inside /* */ comment block
        // When true, we skip characters until we find */
        boolean inBlockComment = false;

        // Process each character one by one
        while (i < len) {

            // Get current character at position i
            char curr = input.charAt(i);

            // Check if next character exists
            // We need 2 characters to detect // or /* or */
            boolean hasNext = (i + 1) < len;

            // Get next character if available, else use space
            char next = hasNext ? input.charAt(i + 1) : ' ';

            // SITUATION 1: Currently inside a block comment /* */
            if (inBlockComment) {

                // Look for the ending */
                if (curr == '*' && next == '/') {

                    // Found end of block comment
                    // Set flag to false - we are out of comment now
                    inBlockComment = false;

                    // Skip both * and / characters
                    // Move pointer by 2 positions
                    i = i + 2;

                } else {

                    // Still inside block comment
                    // Just skip this character, don't add to result
                    i = i + 1;
                }

            }
            // SITUATION 2: Not inside any comment
            else {

                // Check for single-line comment start //
                if (curr == '/' && next == '/') {

                    // Found single-line comment
                    // Skip everything until newline or end of string
                    while (i < len) {

                        // Check if we reached newline
                        if (input.charAt(i) == '\n') {
                            // Stop here, don't skip the newline
                            // Newline will be processed in next iteration
                            break;
                        }

                        // Move to next character
                        i = i + 1;
                    }

                }
                // Check for multi-line comment start /*
                else if (curr == '/' && next == '*') {

                    // Found start of block comment
                    // Set flag to true - we are now inside comment
                    inBlockComment = true;

                    // Skip both / and * characters
                    // Move pointer by 2 positions
                    i = i + 2;

                }
                // Normal character - not part of any comment marker
                else {

                    // This is regular code character
                    // Add it to our result
                    result.append(curr);

                    // Move to next character
                    i = i + 1;
                }
            }
        }

        // Convert StringBuilder to String and return
        return result.toString();
    }

    // Helper method to run one test case
    // Returns true if test passed, false otherwise
    public static boolean testCase(String name, String input, String expected) {

        // Call our main function
        String actual = removeComments(input);

        // Variable to store if test passed
        boolean passed;

        // Handle null comparison separately
        if (expected == null) {
            // Both should be null
            passed = (actual == null);
        } else {
            // Compare strings using equals method
            passed = expected.equals(actual);
        }

        // Print result
        if (passed) {
            // Test passed - print success message
            System.out.println("[PASS] " + name);
        } else {
            // Test failed - print details for debugging
            System.out.println("[FAIL] " + name);
            System.out.println("   Input:    " + formatString(input));
            System.out.println("   Expected: " + formatString(expected));
            System.out.println("   Actual:   " + formatString(actual));
        }

        // Return result
        return passed;
    }

    // Helper to format string for display
    // Shows special characters clearly
    public static String formatString(String s) {

        // Handle null case
        if (s == null) {
            return "null";
        }

        // Replace newline with visible marker
        // This helps us see newlines in output
        return "\"" + s.replace("\n", "\\n") + "\"";
    }

    // Test with large data to check performance
    public static boolean testLargeData() {

        // Create large input string
        StringBuilder bigInput = new StringBuilder();
        StringBuilder bigExpected = new StringBuilder();

        // Generate 50000 iterations for stress test
        for (int i = 0; i < 50000; i++) {

            // Add normal code part
            String code = "x" + i + "=1;";
            bigInput.append(code);
            bigExpected.append(code);

            // Add single line comment (will be removed)
            bigInput.append("//comment" + i);

            // Add newline
            bigInput.append("\n");
            bigExpected.append("\n");
        }

        // Add some block comments
        for (int i = 0; i < 1000; i++) {

            // Add code before block comment
            bigInput.append("a" + i);
            bigExpected.append("a" + i);

            // Add block comment (will be removed)
            bigInput.append("/*block" + i + "*/");

            // Add code after block comment
            bigInput.append("b" + i);
            bigExpected.append("b" + i);
        }

        // Record start time
        long startTime = System.currentTimeMillis();

        // Run the function
        String result = removeComments(bigInput.toString());

        // Record end time
        long endTime = System.currentTimeMillis();

        // Calculate duration
        long duration = endTime - startTime;

        // Print performance info
        System.out.println("   Input size: " + bigInput.length() + " chars");
        System.out.println("   Output size: " + result.length() + " chars");
        System.out.println("   Time: " + duration + " ms");

        // Verify result matches expected
        boolean correct = result.contentEquals(bigExpected);

        // Also verify no comment markers remain
        boolean noSingleComment = !result.contains("//");
        boolean noBlockStart = !result.contains("/*");
        boolean noBlockEnd = !result.contains("*/");

        // All checks must pass
        return correct && noSingleComment && noBlockStart && noBlockEnd;
    }

    // Main method - entry point of program
    public static void main(String[] args) {

        // Counters for test statistics
        int total = 0;
        int passed = 0;

        // Print header
        System.out.println("========================================");
        System.out.println("  REMOVE COMMENTS - TEST RESULTS");
        System.out.println("========================================\n");

        // TEST 1: Given example - single line comment
        total++;
        if (testCase("Test 1: Single-line comment (example 1)",
                "abc//def\nghi",
                "abc\nghi")) {
            passed++;
        }

        // TEST 2: Given example - multi-line comment
        total++;
        if (testCase("Test 2: Multi-line comment (example 2)",
                "ab/*cd*/ef",
                "abef")) {
            passed++;
        }

        // TEST 3: Empty string
        total++;
        if (testCase("Test 3: Empty string",
                "",
                "")) {
            passed++;
        }

        // TEST 4: Null input
        total++;
        if (testCase("Test 4: Null input",
                null,
                null)) {
            passed++;
        }

        // TEST 5: No comments at all
        total++;
        if (testCase("Test 5: No comments",
                "hello world",
                "hello world")) {
            passed++;
        }

        // TEST 6: Only single-line comment
        total++;
        if (testCase("Test 6: Only single-line comment",
                "//everything is comment",
                "")) {
            passed++;
        }

        // TEST 7: Only block comment
        total++;
        if (testCase("Test 7: Only block comment",
                "/*everything*/",
                "")) {
            passed++;
        }

        // TEST 8: Comment at start
        total++;
        if (testCase("Test 8: Comment at beginning",
                "/*start*/hello",
                "hello")) {
            passed++;
        }

        // TEST 9: Comment at end
        total++;
        if (testCase("Test 9: Comment at end",
                "hello/*end*/",
                "hello")) {
            passed++;
        }

        // TEST 10: Multiple single-line comments
        total++;
        if (testCase("Test 10: Multiple single-line comments",
                "a//c1\nb//c2\nc",
                "a\nb\nc")) {
            passed++;
        }

        // TEST 11: Multiple block comments
        total++;
        if (testCase("Test 11: Multiple block comments",
                "a/*1*/b/*2*/c",
                "abc")) {
            passed++;
        }

        // TEST 12: Mixed comments
        total++;
        if (testCase("Test 12: Mixed single and block",
                "a/*block*/b//line\nc",
                "ab\nc")) {
            passed++;
        }

        // TEST 13: Block comment with newline inside
        total++;
        if (testCase("Test 13: Block comment spanning lines",
                "a/*\ncomment\n*/b",
                "ab")) {
            passed++;
        }

        // TEST 14: Single slash (not a comment)
        total++;
        if (testCase("Test 14: Single slash not comment",
                "a/b",
                "a/b")) {
            passed++;
        }

        // TEST 15: Slash and star separately
        total++;
        if (testCase("Test 15: / and * separate",
                "a/ *b",
                "a/ *b")) {
            passed++;
        }

        // TEST 16: Adjacent block comments
        total++;
        if (testCase("Test 16: Adjacent block comments",
                "a/*x*//*y*/b",
                "ab")) {
            passed++;
        }

        // TEST 17: // inside block comment (ignored)
        total++;
        if (testCase("Test 17: // inside block comment",
                "a/* // */b",
                "ab")) {
            passed++;
        }

        // TEST 18: Stars inside block comment
        total++;
        if (testCase("Test 18: Stars in block comment",
                "a/***/b",
                "ab")) {
            passed++;
        }

        // TEST 19: Code with real programming look
        total++;
        if (testCase("Test 19: Real code style",
                "int x = 5; // declare x\nint y = 10; /* y value */",
                "int x = 5; \nint y = 10; ")) {
            passed++;
        }

        // TEST 20: Single line comment without newline at end
        total++;
        if (testCase("Test 20: Single-line no trailing newline",
                "code//comment",
                "code")) {
            passed++;
        }

        // TEST 21: Large data test
        System.out.println("\n--- Large Data Test ---");
        total++;
        if (testLargeData()) {
            System.out.println("[PASS] Test 21: Large data test");
            passed++;
        } else {
            System.out.println("[FAIL] Test 21: Large data test");
        }

        // Print summary
        System.out.println("\n========================================");
        System.out.println("  SUMMARY: " + passed + " / " + total + " tests passed");
        System.out.println("========================================");

        // Final result
        if (passed == total) {
            System.out.println("  STATUS: ALL TESTS PASSED!");
        } else {
            System.out.println("  STATUS: SOME TESTS FAILED!");
        }
    }
}