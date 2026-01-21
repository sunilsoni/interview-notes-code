package com.interview.notes.code.year.y2025.december.microsoft.test5;

public class SmallestString {

    /**
     * True in-place solution using only char[] from input.
     * No StringBuilder, no extra arrays.
     * <p>
     * Key insight: Fill suffix from RIGHT to LEFT so we don't
     * overwrite data before reading it.
     */
    public static String smallestString(String s) {

        // Convert to char array - our only working space
        char[] chars = s.toCharArray();

        // Get length
        int n = chars.length;

        // Counters - just 3 integers
        int bCount = 0;
        int aBeforeFirstC = 0;
        int firstCIndex = -1;

        // Pass 1: Single scan to gather counts
        for (int i = 0; i < n; i++) {

            char ch = chars[i];

            if (ch == 'b') {
                // Count all b's in string
                bCount++;
            } else if (ch == 'c') {
                // Mark first c position only
                if (firstCIndex == -1) {
                    firstCIndex = i;
                }
            } else {
                // Count a's only before first c
                if (firstCIndex == -1) {
                    aBeforeFirstC++;
                }
            }
        }

        // Pass 2: Fill array in-place

        // Step 1: Fill suffix (a's and c's from firstCIndex onwards)
        // We go RIGHT TO LEFT to avoid overwriting unread data
        if (firstCIndex != -1) {

            int readIdx = n - 1;      // Read from end
            int writeIdx = n - 1;     // Write from end

            // Process until we've read all suffix characters
            while (readIdx >= firstCIndex) {

                if (chars[readIdx] != 'b') {
                    // Copy non-b character to write position
                    chars[writeIdx] = chars[readIdx];
                    writeIdx--;       // Move write position left
                }
                readIdx--;            // Always move read position left
            }
        }

        // Step 2: Fill all b's in their position
        // b's go right after the leading a's
        int bStart = aBeforeFirstC;
        int bEnd = aBeforeFirstC + bCount;

        for (int i = bStart; i < bEnd; i++) {
            chars[i] = 'b';
        }

        // Step 3: Fill leading a's
        // These go at the very beginning
        for (int i = 0; i < aBeforeFirstC; i++) {
            chars[i] = 'a';
        }

        // Convert back to string and return
        return new String(chars);
    }

    /**
     * Simple test method
     */
    public static boolean test(String name, String input, String expected) {

        String actual = smallestString(input);
        boolean pass = actual.equals(expected);

        System.out.print(name + ": ");
        System.out.println(pass ? "PASS ✓" : "FAIL ✗");

        if (!pass) {
            System.out.println("  Input: " + input);
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual: " + actual);
        }

        return pass;
    }

    public static void main(String[] args) {

        System.out.println("=== IN-PLACE SOLUTION TESTS ===\n");

        int pass = 0;
        int total = 0;

        // Provided test cases
        total++;
        if (test("Sample 0", "baacba", "aabbca")) pass++;
        total++;
        if (test("Sample 1", "ababbaab", "aaaabbbb")) pass++;
        total++;
        if (test("Example", "abaacbac", "aaabbcac")) pass++;

        // Edge cases
        total++;
        if (test("Single a", "a", "a")) pass++;
        total++;
        if (test("Single b", "b", "b")) pass++;
        total++;
        if (test("Single c", "c", "c")) pass++;
        total++;
        if (test("All a", "aaaa", "aaaa")) pass++;
        total++;
        if (test("All b", "bbbb", "bbbb")) pass++;
        total++;
        if (test("All c", "cccc", "cccc")) pass++;
        total++;
        if (test("Only ab", "baba", "aabb")) pass++;
        total++;
        if (test("Only bc", "cbcb", "bbcc")) pass++;
        total++;
        if (test("Only ac", "caca", "caca")) pass++;
        total++;
        if (test("c blocks", "cab", "bca")) pass++;
        total++;
        if (test("c first", "cba", "bca")) pass++;
        total++;
        if (test("Sorted", "aabbcc", "aabbcc")) pass++;
        total++;
        if (test("Two chars", "ba", "ab")) pass++;

        // Large tests
        System.out.println("\n=== LARGE TESTS ===\n");

        // 100K alternating ab
        StringBuilder in1 = new StringBuilder();
        StringBuilder out1 = new StringBuilder();
        for (int i = 0; i < 50000; i++) in1.append("ab");
        for (int i = 0; i < 50000; i++) out1.append('a');
        for (int i = 0; i < 50000; i++) out1.append('b');

        long t1 = System.currentTimeMillis();
        total++;
        if (test("100K alt ab", in1.toString(), out1.toString())) pass++;
        System.out.println("  Time: " + (System.currentTimeMillis() - t1) + "ms\n");

        // 100K with c blocking
        StringBuilder in2 = new StringBuilder();
        StringBuilder out2 = new StringBuilder();
        in2.append('c');
        for (int i = 0; i < 50000; i++) in2.append('b');
        for (int i = 0; i < 49999; i++) in2.append('a');
        for (int i = 0; i < 50000; i++) out2.append('b');
        out2.append('c');
        for (int i = 0; i < 49999; i++) out2.append('a');

        long t2 = System.currentTimeMillis();
        total++;
        if (test("100K c blocks", in2.toString(), out2.toString())) pass++;
        System.out.println("  Time: " + (System.currentTimeMillis() - t2) + "ms\n");

        // Summary
        System.out.println("=== SUMMARY ===");
        System.out.println("Passed: " + pass + "/" + total);
        System.out.println(pass == total ? "ALL PASS ✓" : "SOME FAILED ✗");
    }
}