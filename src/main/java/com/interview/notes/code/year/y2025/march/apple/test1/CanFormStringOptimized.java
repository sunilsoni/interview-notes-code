package com.interview.notes.code.year.y2025.march.apple.test1;

public class CanFormStringOptimized {

    /**
     * Checks if all letters in B are contained in A (spaces ignored).
     * We can reuse any letter from A any number of times.
     */
    public static boolean canForm(String A, String B) {
        // 1. Create an array to mark which letters appear in A
        boolean[] lettersInA = new boolean[26];

        // 2. Record which letters appear in A (ignore spaces)
        for (char ch : A.toCharArray()) {
            if (ch != ' ') {
                lettersInA[ch - 'a'] = true;
            }
        }

        // 3. Check each letter in B is marked in lettersInA
        for (char ch : B.toCharArray()) {
            if (ch != ' ' && !lettersInA[ch - 'a']) {
                // If B has a letter not in A, return false
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Some quick tests
        System.out.println(canForm("northern lights", "night"));  // true
        System.out.println(canForm("traveler", "arave"));         // true
        System.out.println(canForm("never mind", "tidy"));        // false
    }
}