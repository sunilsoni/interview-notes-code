package com.interview.notes.code.months.march24.test20;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        // Use a set to store characters encountered in the current window
        Set<Character> charSet = new HashSet<>();

        while (right < s.length()) {
            char currentChar = s.charAt(right);

            // If the character is already present in the current window (represented by the set),
            // shrink the window from the left side until the repeating character is removed.
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }

            // Add the new character to the window (set)
            charSet.add(currentChar);

            // Update the maximum length if the current window size is larger
            maxLength = Math.max(maxLength, right - left + 1);

            right++;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String str1 = "abcabebb";
        int length1 = lengthOfLongestSubstring(str1);
        System.out.println("Length of longest substring without repeating characters in \"" + str1 + "\": " + length1); // Output: 3

        String str2 = "bbbbb";
        int length2 = lengthOfLongestSubstring(str2);
        System.out.println("Length of longest substring without repeating characters in \"" + str2 + "\": " + length2); // Output: 1

        String str3 = "pwwkew";
        int length3 = lengthOfLongestSubstring(str3);
        System.out.println("Length of longest substring without repeating characters in \"" + str3 + "\": " + length3); // Output: 3
    }
}
