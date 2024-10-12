package com.interview.notes.code.months.oct24.test6;

import java.util.Stack;

public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        String[] testCases = {
                "bcabc",
                "cbacdcbc",
                "abacb",
                "zzyyxxwwvvuuttssrrqqppoonnmmllkkjjiihhggffeeddbba",
                "thequickbrownfoxjumpsoverthelazydog",
                "a",
                "",
                "aabbccddeffghijklmnopqrstuvwxyz"
        };

        String[] expectedOutputs = {
                "abc",
                "acdb",
                "abc",
                "zyxwvutsrqponmlkjihgfedb",
                "thequickbrownfxjmpsvlazydg",
                "a",
                "",
                "abcdefghijklmnopqrstuvwxyz"
        };

        for (int i = 0; i < testCases.length; i++) {
            String result = removeDuplicateLetters(testCases[i]);
            boolean passed = result.equals(expectedOutputs[i]);
            System.out.println("Test Case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("  Input: " + testCases[i]);
                System.out.println("  Expected: " + expectedOutputs[i]);
                System.out.println("  Got: " + result);
            }
        }
    }

    public static String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        boolean[] seen = new boolean[26];
        Stack<Integer> stack = new Stack<>();

        // Record the last index of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (!seen[c]) {
                while (!stack.isEmpty() && stack.peek() > c && i < lastIndex[stack.peek()]) {
                    seen[stack.pop()] = false;
                }
                stack.push(c);
                seen[c] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : stack) {
            sb.append((char) (i + 'a'));
        }
        return sb.toString();
    }
}
