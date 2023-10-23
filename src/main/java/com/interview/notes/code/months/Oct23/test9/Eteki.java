package com.interview.notes.code.months.Oct23.test9;

import java.util.*;


/**
 *
 * Algorithm:
 * Given 2 strings, write a function that returns true if the entirety of the character set of String magazine could
 * comprise the character set of String note. Real world explanation is that you have a magazine and a note; you want
 * to cut out letters from the magazine to create the note. Does the Magazine have enough of each letter to create the
 * note? (Needs the correct letters as well as the correct number of each, based on what the Note contains.)
 * assertTrue( isSubset( "fire", "french fries"))
 * assertTrue( isSubset( "foodie", "good french fries"))
 * assertFalse( isSubset( "foodie", "dogs eating french fries" ))
 *
 *
 *
 * Yes, the solution can be optimized further, especially in terms of space complexity. In the previous solution, two hash maps were used to store the character counts of both strings. However, we can optimize the solution to use just one hash map, which will store the character counts from the magazine string. As we iterate through the note string, we can decrement the count in the hash map for each character. If we encounter a character that's not in the hash map or its count falls below zero, we return false. This way, we only need to iterate through both strings once, and we only need one hash map, which saves space.
 *
 * Complexity Analysis:
 *
 * Time Complexity:
 *
 * The time complexity remains the same, O(n + m), where n is the length of note and m is the length of magazine.
 * Space Complexity:
 *
 * The space complexity has been improved to O(m) as we are now using only one hash map for magazine character counts instead of two hash maps.
 *
 */
public class Eteki {

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
        
        // Character Counting for magazine
        Map<Character, Integer> magazineCharCount = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            magazineCharCount.put(c, magazineCharCount.getOrDefault(c, 0) + 1);
        }
        
        // Decrement count while iterating through note
        for (char c : note.toCharArray()) {
            int count = magazineCharCount.getOrDefault(c, 0);
            if (count == 0) {
                return false;  // Insufficient character count in magazine
            }
            magazineCharCount.put(c, count - 1);  // Decrement the count
        }
        
        // All characters in note have sufficient counts in magazine
        return true;
    }
}
