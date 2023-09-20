package com.interview.notes.code.months.Aug23.test3;

public class StringPermutation {
    public static void main(String[] args) {
        // Input string
        String str = "abc";
        // Start generating permutations
        generatePermutations(str, 0, str.length() - 1);
    }

    /**
     * Generate and print all permutations of a string.
     *
     * @param str   The string for which to generate permutations
     * @param start The start index for permutation
     * @param end   The end index for permutation
     */
    public static void generatePermutations(String str, int start, int end) {
        // Base case: when start and end pointers are the same, print the permutation
        if (start == end) {
            System.out.println(str);
        } else {
            for (int i = start; i <= end; i++) {
                // Swap the current element with itself and all subsequent elements
                str = swap(str, start, i);
                // Generate all permutations for the remaining elements
                generatePermutations(str, start + 1, end);
                // Backtrack to original configuration (un-swap)
                str = swap(str, start, i);
            }
        }
    }

    /**
     * Swap characters at positions x and y in the string.
     *
     * @param str The original string
     * @param x   Position x
     * @param y   Position y
     * @return The new string after swap
     */
    public static String swap(String str, int x, int y) {
        char[] charArray = str.toCharArray();
        char temp = charArray[x];
        charArray[x] = charArray[y];
        charArray[y] = temp;
        return new String(charArray);
    }
}
