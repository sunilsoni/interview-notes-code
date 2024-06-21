package com.interview.notes.code.months.june24.test7;

//PARTIAL WORKING
/*
Explanation:

 longestPalindrome Function: This function generates the longest possible palindrome from a given string by counting the frequency of each character. It ensures that the middle character (if any) is the smallest lexicographically possible character.

combinePalindromes Function: This function combines the characters from the two longest palindromes generated from the input strings. It ensures that the combined palindrome is the longest possible and if there are multiple options, it chooses the alphabetically smallest one. The middle character is chosen correctly if it exists.

 main Function: This function reads the number of test cases and the input strings for each test case, then prints the resulting merged palindrome for each example.

Description
Given two strings, find all palindromes that can be formed with the letters of each string. From those palindromes, select one from each set that, when combined and rearranged, produces the longest palindrome possible. If there are multiple palindromes of that length, choose the alphabetically smallest of them.
Example
s1 = 'aabbc'
s2 = 'ddefefa'
All of the letters of the first string can make a palindrome. The choices using all letters are [abcba, bacab].
All of the letters of the second string can make a palindrome. The choices using all letters are [defqfed, dfeqefd, edfqfde, efdqdfe, fdeqedf,
fedqdef].
The two longest results in s1 have a length of 5.
The six longest results in s2 have a length of 6.
From the longest results for s1 and s2, merge the two that form the lowest merged palindrome, alphabetically. In this case, choose abba and defqfed. The two palindromes can be combined to form a single palindrome if either the c or the q is discarded. The alphabetically smallest combined palindrome is abdefcfedba.

Function Description
Complete the function mergePalindromes in the editor below. The function must return a string.
mergePalindromes has the following parameter(s):
string 51: a string string s2: a string
Constraints
・ 1≤1s11≤105
• 15|52|5105
• s1 and s2 contain only lowercase English letters in the range ascilla-z].
• Input Format For Custom Testing
• Sample Case 0
Sample Input For Custom Testing
Copy And
Shar
Ask
•1
Sample Output
acaca
STDIN
Function


aab
→ s1 = 'aab'
cca
→ s2 = ‘cca'

Constraints
• 15|51|≤ 105
• 1 5|52|≤ 105
• s1 and s2 contain only lowercase English letters the range ascii[a-z].
• Input Format For Custom Testing
• Sample Case 0
Sample Input For Custom Testing
STDIN
ーーーーー
aab
сса
Function
→ s1 = 'aab' → s2 = 'cca'
Sample Output
acaca
Explanation
Characters [l'a', 'a'] can be picked from the first string to form the palindrome 'aa'. Characters I'
'c', 'a'] can be picked from the second string to form the palindrome 'cac' and merged with 'aa' to form 'acaca'.
• Sample Case 1
 */
public class Solution3 {

    public static String mergePalindromes(String s1, String s2) {
        String palindrome1 = longestPalindrome(s1);
        String palindrome2 = longestPalindrome(s2);

        return combinePalindromes(palindrome1, palindrome2);
    }

    private static String longestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middle = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                if (middle == 0 || (char) (i + 'a') < middle) {
                    middle = (char) (i + 'a');
                }
            }
            for (int j = 0; j < freq[i] / 2; j++) {
                half.append((char) (i + 'a'));
            }
        }

        String halfStr = half.toString();
        StringBuilder palindrome = new StringBuilder(halfStr);
        if (middle != 0) {
            palindrome.append(middle);
        }
        palindrome.append(half.reverse().toString());

        return palindrome.toString();
    }

    private static String combinePalindromes(String p1, String p2) {
        int[] freq = new int[26];
        for (char c : p1.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : p2.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middle = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                if (middle == 0 || (char) (i + 'a') < middle) {
                    middle = (char) (i + 'a');
                }
            }
            for (int j = 0; j < freq[i] / 2; j++) {
                half.append((char) (i + 'a'));
            }
        }

        String halfStr = half.toString();
        StringBuilder result = new StringBuilder(halfStr);
        if (middle != 0) {
            result.append(middle);
        }
        result.append(half.reverse().toString());

        return result.toString();
    }

    public static void main(String[] args) {
        // Example 1
        String s1 = "adaab";
        String s2 = "cac";
        System.out.println("Example 1:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println();

        // Example 2
        s1 = "awwzaigvxuikdqlvshspsvyckttwdzqmarpxglwmpob";
        s2 = "disfxyobndu";
        System.out.println("Example 2:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println();
    }
}
