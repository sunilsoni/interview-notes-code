package com.interview.notes.code.months.april24.test3;

/**
 *
 Java:

 Searching Challenge
 Have the function SearchingChallenge (str) take the str parameter being passed and find the longest palindromic substring, which means the longest substring which is read the same forwards as it is backwards. For example: if str is "abracecars" then your program should return the string racecar because it is the longest palindrome within the input string.
 The input will only contain lowercase alphabetic characters. The longest palindromic substring will always be unique, but if there is none that is longer than 2 characters, return the string none.
 Examples
 Input: "hellosannasmith"
 Output: sannas
 Input: "abcdefgg"
 Output: none

 import java.util.*;
 import java. 10.*;
 class Main {
 public static String SearchingChallenge (String str) {
 // code goes here
 return str;
 }
 public static void main (Stringll args) {
 // keep this function call here
 Scanner s = new Scanner (System. in);
 System. out. print (SearchingChallenge(s.nextLine()));
 ｝


 */
class SearchingChallenge {

    public static String SearchingChallenge(String str) {
        if (str == null || str.length() < 3) {
            return "none";
        }

        String longestPalindrome = "";

        for (int i = 0; i < str.length(); i++) {
            // Check for longest odd length palindrome with center at i
            String palindrome = findLongestPalindromeAroundCenter(str, i, i);
            if (palindrome.length() > longestPalindrome.length()) {
                longestPalindrome = palindrome;
            }

            // Check for longest even length palindrome with center at i, i+1
            palindrome = findLongestPalindromeAroundCenter(str, i, i + 1);
            if (palindrome.length() > longestPalindrome.length()) {
                longestPalindrome = palindrome;
            }
        }

        return longestPalindrome.length() > 2 ? longestPalindrome : "none";
    }

    private static String findLongestPalindromeAroundCenter(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return str.substring(left + 1, right);
    }

    public static void main (String[] args) {

        System.out.println(SearchingChallenge("hellosannasmith"));
        System.out.println(SearchingChallenge("abcdefgg"));
    }
}
