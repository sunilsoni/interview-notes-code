package com.interview.notes.code.year.y2024.march24.test13;


/**
 * 1. Question 1
 * The discovery of the structure of DNA in 1953 was made possible by Dr. Franklin's X-ray diffraction. Dr. Franklin's creation of the famous Photo 51 demonstrated the double-helix structure of deoxyribonucleic acid. Palindromic structures are widely studied in string processing and combinatorics and have applications in the analysis of DNA.
 * During her studies, Rosalind had come across a problem where she was given a string s consisting of lowercase English letters and the character '?'. She was required to develop an algorithm that would interpolate the string by replacing the question marks with lowercase English letters such that it can be rearranged to form a palindrome. If there were more than one palindromes possible, it had to be lexicographically smallest one, and if there were none, the algorithm would return "-1" as the answer.
 * Note:
 * • A String p is lexicographically smaller than string q, if pis a prefix of q and pis not equal to q or there exists i, such that p;< q; and for all j< i. it is satisfied that pj= 9j-
 * • A palindrome is a string that reads the same backward as forward; for example, strings "z", "aaa", "aba", "abccba" are palindromes, but strings "hackerland", "reality", and "ab" are not.
 * Example
 * Given, s= "axxb??".
 * The two question marks can be replaced with the characters 'a' and 'b' respectively to form a string 5 = "axxbab". The string can be
 * rearranged to "abba" which is the lexicographically smallest palindrome possible by interpolating the string.
 * Function Description
 * Complete the function getSmallestPalindrome in the editor below.
 * getSmallestPalindrome has the following parameter:
 * strings: a string
 * Returns
 * string: the lexicographically smallest palindrome possible or -1
 * Constraints
 * • 1 ≤ /5/≤ 105
 * • String s contains only lowercase English letters and '?'s.
 * <p>
 * <p>
 * <p>
 * • Input Format For Custom Testing
 * The first line contains a string, s.
 * • Sample Case 0
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * a?rt???
 * →
 * s= "a?rt???"
 * Sample Output
 * aartraa
 * Explanation
 * The question marks can be replaced with 'a', 'r', 'a', and 'a' to form the palindrome "aartraa".
 * • Sample Case 1
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * yh??tx
 * s = "yh??tx"
 * Sample Output
 * - 1
 * Explanation
 * It is not possible to replace '?'s in such a way that the string can be rearranged to form a palindrome.
 */

//WORKING
public class FranklinSmallestPalindrome {


    public static String getSmallestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int[] count = new int[26]; // To count occurrences of each letter
        int questionMarks = 0;

        // Count occurrences of each letter and count '?'s
        for (char c : chars) {
            if (c == '?') {
                questionMarks++;
            } else {
                count[c - 'a']++;
            }
        }

        // Check if it's possible to form a palindrome
        if (!isPalindromePossible(count, questionMarks)) {
            return "-1";
        }

        // Fill in '?'s with appropriate characters to form the lexicographically smallest palindrome
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (chars[left] == '?' && chars[right] == '?') {
                // Fill both '?'s with 'a'
                chars[left] = 'a';
                chars[right] = 'a';
                questionMarks -= 2;
            } else if (chars[left] == '?') {
                // Fill '?' on the left with the character on the right
                chars[left] = chars[right];
                questionMarks--;
            } else if (chars[right] == '?') {
                // Fill '?' on the right with the character on the left
                chars[right] = chars[left];
                questionMarks--;
            }
            left++;
            right--;
        }

        // If there's a remaining '?' in the middle, fill it with 'a'
        if (chars.length % 2 == 1 && chars[chars.length / 2] == '?') {
            chars[chars.length / 2] = 'a';
            questionMarks--;
        }

        // If there are remaining '?'s, fill them with 'a' as well
        for (int i = 0; i < chars.length && questionMarks > 0; i++) {
            if (chars[i] == '?') {
                chars[i] = 'a';
                questionMarks--;
            }
        }

        return new String(chars);
    }

    private static boolean isPalindromePossible(int[] count, int questionMarks) {
        int oddCount = 0;
        for (int i : count) {
            if (i % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount <= questionMarks;
    }

    public static void main(String[] args) {
        System.out.println(getSmallestPalindrome("ai?a??u")); // should output "aaiuiaa"//NOT WORKING
        System.out.println(getSmallestPalindrome("a?rt???")); // should output "aartraa"
        System.out.println(getSmallestPalindrome("a?x??b")); // should output "abxxba"//NOT WORKING
        System.out.println(getSmallestPalindrome("y?h??tx")); // should output "-1"
        System.out.println(getSmallestPalindrome("yh??tx")); // should output "-1"
        System.out.println(getSmallestPalindrome("a?rt???")); // should output "aartraa"
    }
}
