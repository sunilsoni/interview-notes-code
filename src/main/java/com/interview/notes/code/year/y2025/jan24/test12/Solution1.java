package com.interview.notes.code.year.y2025.jan24.test12;

/*
/**
 * Instructions to candidate.
 * 1) Run this code in the REPL to observe its behavior. The execution entry point is main().
 * 2) Consider adding some additional tests in doTestsPass().
 * 3) Implement reverseStr(String str) correctly.
 * 4) If time permits, some possible follow-ups.


 */
public class Solution1 {
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }

    public static String reverseStr(String str) {
        if (str == null) return null;
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    public static boolean doTestsPass() {
        boolean result = true;

        // Basic tests
        result = result && reverseStr("abcd").equals("dcba");
        result = result && reverseStr("").equals("");
        result = result && reverseStr("a").equals("a");
        result = result && reverseStr("ab").equals("ba");

        // Edge cases
        result = result && (reverseStr(null) == null);
        result = result && reverseStr("A man, a plan, a canal, Panama").equals("amanaP ,lanac a ,nalp a ,nam A");

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("x");
        }
        String reversedLarge = reverseStr(largeInput.toString());
        result = result && reversedLarge.length() == 100000;

        return result;
    }
}
