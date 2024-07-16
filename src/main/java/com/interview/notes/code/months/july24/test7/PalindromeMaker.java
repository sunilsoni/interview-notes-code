package com.interview.notes.code.months.july24.test7;

/*
We should consider all possible corner cases and handle large input efficiently.

Write a function solution that, given a string S of length N, returns any palindrome which can be obtained by replacing all of the question marks in S by lowercase letters ('a'-*z). If no palindrome can be obtained, the function should return the string
"NO".
A palindrome is a string that reads the same both forwards and backwards. Some examples of palindromes are: "kayak", "radar",
"mom".
Examples:
1. Given S = "?ab??a".
", the function should return "aabbaa".
2. Given S = "bab??a", the function should return "NO".
3. Given S = "?a?", the function may return "aaa". It may also return
"zaz", among other possible answers. The function is supposed to return only one of the possible answers.
Assume that:
• N is an integer within the range [1..1,000];
• string S consists only of lowercases letters ('a' - 'Z') or '?'.
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 */
public class PalindromeMaker {
    public static void main(String[] args) {
        PalindromeMaker maker = new PalindromeMaker();

        // Example 1
        String example1 = "?ab??a";
        System.out.println("Input: " + example1);
        System.out.println("Output: " + maker.solution(example1)); // Expected: "aabbaa"

        // Example 2
        String example2 = "bab??a";
        System.out.println("Input: " + example2);
        System.out.println("Output: " + maker.solution(example2)); // Expected: "NO"

        // Example 3
        String example3 = "?a?";
        System.out.println("Input: " + example3);
        System.out.println("Output: " + maker.solution(example3)); // Expected: "aaa" or any other valid palindrome like "zaz"

        // Additional Example
        String example4 = "??";
        System.out.println("Input: " + example4);
        System.out.println("Output: " + maker.solution(example4)); // Expected: "aa" or "zz" etc.
    }

    public String solution(String S) {
        char[] chars = S.toCharArray();
        int n = chars.length;

        for (int i = 0; i < n / 2; i++) {
            int j = n - i - 1;

            if (chars[i] == '?' && chars[j] == '?') {
                chars[i] = chars[j] = 'a';
            } else if (chars[i] == '?') {
                chars[i] = chars[j];
            } else if (chars[j] == '?') {
                chars[j] = chars[i];
            } else if (chars[i] != chars[j]) {
                return "NO";
            }
        }

        if (n % 2 == 1 && chars[n / 2] == '?') {
            chars[n / 2] = 'a';
        }

        return new String(chars);
    }
}
