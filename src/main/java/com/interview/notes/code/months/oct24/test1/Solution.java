package com.interview.notes.code.months.oct24.test1;

/*

There is a string of length N made only of letters "a". Whenever there are two identical adjacent letters (e.g. "aa"), they can be transformed into a single letter that is the next letter of the alphabet. For example, "aa" can be transformed into "b" and "ee" into "f".
". However, "zz" cannot be further
transformed.
What is the alphabetically largest string that can be obtained from the initial string?
Write a function:
def solution(N)
that, given an integer N, returns the alphabetically largest string that can be obtained after such transformations.
Examples:
1. Given N = 11, the function should return "dba". The initial string
"aaaaaaaaaaa" can be transformed in the following manner: "aaaaaaaaaaa" →
"bbbbba" → "ccba" → "dba".
2. Given N = 1, the function should return "a".
". The initial string "a" cannot be
transformed in any way.
3. Given N = 67108876, the function should return "zzdc".
Write an efficient algorithm for the following assumptions:
• N is an integer within the range [1.1,000,000,000].

 */
public class Solution {
    public String solution(int N) {
        int[] counts = new int[26]; // counts for letters 'a' to 'z'
        counts[0] = N; // Start with N 'a's

        // Perform transformations from 'a' to 'y'
        for (int i = 0; i < 25; i++) {
            counts[i + 1] += counts[i] / 2; // Transform pairs to the next letter
            counts[i] %= 2; // Keep the remainder
        }

        // Handle 'z's (we can only have up to 25 'z's)
        counts[25] %= 26;

        // Build the result string from 'z' to 'a' for lex smallest order
        StringBuilder result = new StringBuilder();
        for (int i = 25; i >= 0; i--) {
            for (int j = 0; j < counts[i]; j++) {
                result.append((char) ('a' + i));
            }
        }

        return result.toString();
    }

    // Test cases to verify the solution
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test case 1
        String result = sol.solution(11);
        System.out.println("Test Case 1: " + (result.equals("dba")) + " " + result);

        // Test case 2
        result = sol.solution(1);
        System.out.println("Test Case 2: " + (result.equals("a")) + " " + result);

        // Test case 3
        result = sol.solution(67108876);
        System.out.println("Test Case 3: " + (result.equals("zzdc")) + " " + result);
    }
}
