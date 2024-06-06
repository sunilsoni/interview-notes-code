package com.interview.notes.code.months.may24.test14;

/**
 * WORKING
 * <p>
 * java program  remove all adjustment duplicate values
 * <p>
 * Input:  abbbabc
 * <p>
 * Output:bc
 * <p>
 * <p>
 * Explanation:
 * <p>
 * Step1: abbbabc
 * Step2: aabc
 * Step3: bc
 */
public class RemoveAdjacentDuplicates {
    public static void main(String[] args) {
        String input = "abbbabc";
        String result = removeAdjacentDuplicates(input);
        System.out.println("Output: " + result);
    }

    public static String removeAdjacentDuplicates(String s) {
        boolean hasAdjacentDuplicates = true;

        while (hasAdjacentDuplicates) {
            StringBuilder sb = new StringBuilder();
            hasAdjacentDuplicates = false;
            int i = 0;

            while (i < s.length()) {
                // Check if the current character is the same as the next character
                if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                    // Skip all adjacent duplicates
                    while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                        i++;
                    }
                    hasAdjacentDuplicates = true;
                } else {
                    // Add the current character to the StringBuilder
                    sb.append(s.charAt(i));
                }
                i++;
            }

            // Update the string for the next iteration
            s = sb.toString();
        }

        return s;
    }
}
