package com.interview.notes.code.months.Oct23.test9;

import java.util.HashMap;
import java.util.Map;

/**
 * Algorithm:
 * Given 2 strings, write a function that returns true if the entirety of the character set of String magazine could
 * comprise the character set of String note. Real world explanation is that you have a magazine and a note; you want
 * to cut out letters from the magazine to create the note. Does the Magazine have enough of each letter to create the
 * note? (Needs the correct letters as well as the correct number of each, based on what the Note contains.)
 * assertTrue( isSubset( "fire", "french fries"))
 * assertTrue( isSubset( "foodie", "good french fries"))
 * assertFalse( isSubset( "foodie", "dogs eating french fries" ))
 * <p>
 * <p>
 * Complexity Analysis:
 * <p>
 * Time Complexity:
 * <p>
 * The time complexity is primarily driven by the iterations through the note and magazine strings, as well as the iteration through the noteCharCount map.
 * Let n be the length of note and m be the length of magazine.
 * The getCharCount method has a time complexity of O(n) and O(m) for note and magazine respectively.
 * The iteration through noteCharCount map has a time complexity of O(n).
 * Thus, the overall time complexity is O(n + m).
 * Space Complexity:
 * <p>
 * The space complexity is driven by the additional data structures used, which are the noteCharCount and magazineCharCount maps.
 * The space complexity is O(n + m).
 */
public class Eteki1 {

    public static void main(String[] args) {
        System.out.println(isSubset("fire", "french fries"));  // Expected: true
        System.out.println(isSubset("foodie", "good french fries"));  // Expected: true
        System.out.println(isSubset("foodie", "dogs eating french fries"));  // Expected: false
    }

    public static boolean isSubset(String note, String magazine) {
        // Input Validation
        if (note == null || magazine == null) {
            return false;
        }

        // Character Counting
        Map<Character, Integer> noteCharCount = getCharCount(note);
        Map<Character, Integer> magazineCharCount = getCharCount(magazine);

        // Character Set Comparison
        for (Map.Entry<Character, Integer> entry : noteCharCount.entrySet()) {
            char charKey = entry.getKey();
            if (magazineCharCount.getOrDefault(charKey, 0) < entry.getValue()) {
                return false;  // Insufficient character count in magazine
            }
        }

        // All characters in note have sufficient counts in magazine
        return true;
    }

    public static Map<Character, Integer> getCharCount(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        return charCount;
    }
}
