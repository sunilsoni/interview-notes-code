package com.interview.notes.code.months.aug24.amz.test16;

public class SpecialStringGenerator5 {

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
                i++; // Backtrack to try the next character
            }
        }
        return "-1";
    }

    private static boolean makeSpecial(char[] chars, int start) {
        for (int i = start; i < chars.length; i++) {
            if (i > 0 && chars[i] == chars[i - 1]) {
                if (chars[i] == 'z') {
                    return false;
                }
                chars[i]++;
                for (int j = i + 1; j < chars.length; j++) {
                    chars[j] = 'a';
                }
                i--; // Check this position again
            }
        }
        return true;
    }
}
