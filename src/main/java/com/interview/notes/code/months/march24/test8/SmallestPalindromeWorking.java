package com.interview.notes.code.months.march24.test8;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 1. Question 1
 The discovery of the structure of DNA in 1953 was made possible by Dr. Franklin's X-ray diffraction. Dr. Franklin's creation of the famous Photo 51 demonstrated the double-helix structure of deoxyribonucleic acid. Palindromic structures are widely studied in string processing and combinatorics and have applications in the analysis of DNA.
 During her studies, Rosalind had come across a problem where she was given a string s consisting of lowercase English letters and the character '?'. She was required to develop an algorithm that would interpolate the string by replacing the question marks with lowercase English letters such that it can be rearranged to form a palindrome. If there were more than one palindromes possible, it had to be lexicographically smallest one, and if there were none, the algorithm would return "-1" as the answer.
 Note:
 • A String p is lexicographically smaller than string q, if pis a prefix of q and pis not equal to q or there exists i, such that p; < q; and for all j< i. it is satisfied that pj= qj
 • A palindrome is a string that reads the same backward as forward; for example, strings "z", "aaa", "aba", "abccba" are palindromes, but strings "hackerland", "reality", and "ab" are not.
 Example
 Given, 5 = "axxb??".
 The two question marks can be replaced with the characters 'a' and 'b' respectively to form a string 5 = "axxbab". The string can be
 rearranged to "abba" which is the lexicographically smallest palindrome possible by interpolating the string.
 Function Description
 Complete the function getSmallestPalindrome in the editor below.
 getSmallestPalindrome has the following parameter:
 strings: a string
 Returns
 string: the lexicographically smallest palindrome possible or -1
 Constraints
 •1≤/5/≤105
 • String s contains only lowercase English letters and '?'s.



 Constraints
 ・ 1≤/5/≤105
 • String s contains only lowercase English letters and '?'s.
 • Input Format For Custom Testing
 The first line contains a string, s.
 • Sample Case 0
 Sample Input For Custom Testing
 STDIN
 ーーーーー
 a?rt???
 FUNCTION
 S =
 "a?rt???"
 Sample Output
 aartraa
 Explanation
 The question marks can be replaced with 'a', 'r', 'a', and 'a' to form the palindrome "aartraa".
 • Sample Case 1
 Sample Input For Custom Testing
 STDIN
 FUNCTION
 yh??tx
 →
 s = "yh??tx"
 Sample Output
 -1
 Explanation
 It is not possible to replace '?'s in such a way that the string can be rearranged to form a palindrome.


 */
class SmallestPalindromeWorking {
    public static void main(String[] args) {
        String input = "a?rt???";
        System.out.println(getSmallestPalindrome(input));

        String input1 = "ai??a?u";
        System.out.println(getSmallestPalindrome(input1));
    }


    public static String getSmallestPalindrome2(String s) {
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '?') {
                charCount[s.charAt(i) - 'a']++;
            }
        }

        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount > 1) return "-1";

        char[] result = new char[s.length()];
        int start = 0, end = s.length() - 1;
        char oddChar = 0;

        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            while (charCount[i] >= 2) {
                result[start++] = c;
                result[end--] = c;
                charCount[i] -= 2;
            }
            if (charCount[i] == 1) oddChar = c;
        }

        if (oddChar != 0) {
            result[start] = oddChar;
        }

        return new String(result);
    }

    //WORKING
    public static String getSmallestPalindrome(String s) {
        char[] str = s.toCharArray();
        int left = 0, right = s.length() - 1;

        while (left <= right) {
            if (str[left] == '?' && str[right] == '?') {
                str[left] = str[right] = 'a';
            } else if (str[left] == '?') {
                str[left] = str[right];
            } else if (str[right] == '?') {
                str[right] = str[left];
            } else if (str[left] != str[right]) {
                return "-1";
            }
            left++;
            right--;
        }

        return new String(str);
    }

}