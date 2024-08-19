package com.interview.notes.code.months.aug24.amz.test16;

public class SpecialStringGenerator {

    public static void main(String[] args) {


        // Example test cases
        String[] testCases = {
                "abbd",
                "abccde",
                "zzab",
                "xyzxyx",
                "abc",
                "a",
                "vbyetnnzjgqihoyzodkisanxbapavajjzlzocftmwnwvmugrmgsmmdhuyvxcacghlfwcxgeskzcjeywbwpkxorpauuwriigmsobq",
                "wvwqmsosoqpovzy"
        };

        for (String testCase : testCases) {
            String result = getSpecialString(testCase);
            System.out.println("Input: " + testCase + " -> Output: " + result);
        }

    }

    public static String getSpecialString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] < 'z') {
                chars[i]++;
                for (int j = i + 1; j < n; j++) {
                    chars[j] = 'a';
                }
                if (makeSpecial(chars, i)) {
                    return new String(chars);
                }
            }
            // Reset current character and continue to previous
            chars[i] = s.charAt(i);
        }
        return "-1";
    }

    private static boolean makeSpecial(char[] chars, int start) {
        for (int i = Math.max(1, start); i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                if (chars[i] == 'z') {
                    return false;
                }
                chars[i]++;
                for (int j = i + 1; j < chars.length; j++) {
                    chars[j] = 'a';
                }
                i = Math.max(1, i - 1); // Check this position again
            }
        }
        return true;
    }
}
