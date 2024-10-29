package com.interview.notes.code.months.oct24.test20;

public class TikTokStringChallenge {

    public static int getMinTransformations(String caption) {
        int n = caption.length();
        int changes = 0;

        // Convert string to character array for easier manipulation
        char[] chars = caption.toCharArray();

        for (int i = 1; i < n; i++) {
            // If current character is different from the previous one
            if (chars[i] != chars[i - 1]) {
                // Check the optimal character to switch to
                if (i < n - 1 && chars[i + 1] == chars[i - 1]) {
                    chars[i] = chars[i - 1];
                    changes++;
                } else {
                    // No match on the next one, change to previous
                    chars[i] = chars[i - 1];
                    changes++;
                }
            }
        }

        return changes;
    }

    public static void main(String[] args) {
        // Provided test cases
        test("abab", 2);
        test("abcdef", 3);
        test("aca", 2);

        // Additional edge cases
        test("aaaa", 0);  // Already meets the rule
        test("ab", 1);  // One change needed
        test("aabbccdd", 0);  // Already meets the rule
        test("a", 0);  // Single letter, edge case

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append(i % 2 == 0 ? 'a' : 'b');
        }
        test(largeInput.toString(), 50000);
    }

    private static void test(String caption, int expected) {
        int result = getMinTransformations(caption);
        if (result == expected) {
            System.out.println("PASS for input: " + caption);
        } else {
            System.out.println("FAIL for input: " + caption + ". Expected: " + expected + ", but got: " + result);
        }
    }
}
