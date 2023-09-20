package com.interview.notes.code.months.Aug23.test3;

import java.util.HashMap;

public class RemoveRepeatingChars {
    public static void main(String[] args) {
        String str = "aabbccddeeff";
        String result = removeRepeatingChars(str);
        System.out.println("String after removing repeating characters: " + result);
    }

    /**
     * Removes all repeating characters from a string.
     *
     * @param str The input string.
     * @return The string with all repeating characters removed.
     */
    public static String removeRepeatingChars(String str) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();  // To store character frequencies
        StringBuilder sb = new StringBuilder();  // To build the result string

        // First pass: count frequencies
        for (char ch : str.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

        // Second pass: build the result string
        for (char ch : str.toCharArray()) {
            if (charCountMap.get(ch) == 1) {
                sb.append(ch);  // Append the character if it does not repeat
            }
        }

        return sb.toString();  // Return the resulting string
    }
}
