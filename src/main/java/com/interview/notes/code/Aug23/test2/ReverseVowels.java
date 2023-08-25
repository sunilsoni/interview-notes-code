package com.interview.notes.code.Aug23.test2;

/*
In Java explain below and proide code and runtime complexty:

Given a string s, reverse only all the vowels in the string and return it.
The vowels are 'a', *e*. 'i‘, 'o', and 'u'. and they can appear in both lower and upper cases, more than once.
Example 1:
Input: s = "hello"
Output: "holle"
Example 2:
Input: s = "leetcode"
Output: "leotcede"
Constraints:
• 1 <= s.length <= 3 * 105
s consist of printable ASCII characters.
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray(); // Convert the string to a character array for easy manipulation
        int i = 0, j = chars.length - 1; // Initialize the pointers
        String vowels = "aeiouAEIOU"; // Define the vowels
        
        while (i < j) {
            // Find the next vowel from the beginning
            while (i < j && vowels.indexOf(chars[i]) == -1) {
                i++;
            }
            
            // Find the next vowel from the end
            while (i < j && vowels.indexOf(chars[j]) == -1) {
                j--;
            }
            
            // Swap the vowels
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            
            // Move both pointers
            i++;
            j--;
        }
        
        return new String(chars); // Convert the character array back to a string and return
    }
}
