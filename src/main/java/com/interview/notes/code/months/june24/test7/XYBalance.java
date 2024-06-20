package com.interview.notes.code.months.june24.test7;

/**
 * //An input string is considered xy-balanced if,
 * // for every occurrence of the character 'x'
 * // there exists at least one occurrence of the character 'y' later in the string.
 * // In other words:
 * // If the string contains 'x', there must be a corresponding 'y' (possibly after other characters) to balance it.
 * // Multiple 'x's can be balanced by a single "y'.
 * // For example:
 * // "xxy" is balanced because both 'x's have a corresponding "y'.
 * // "xyx" is not balanced because the first "x' has no matching "y'.
 * // Now, let's write a function that checks whether a given string is xy-balanced.
 */
public class XYBalance {
    public static void main(String[] args) {
        XYBalance myClass = new XYBalance();
        // Test cases
        System.out.println(myClass.xyBalance("aaxbby")); // Output: true
        System.out.println(myClass.xyBalance("xxbxy")); // Output: true
        System.out.println(myClass.xyBalance("aaxbb")); // Output: false
        System.out.println(myClass.xyBalance("yaaxbb")); // Output: false
        System.out.println(myClass.xyBalance("x")); // Output: false
        System.out.println(myClass.xyBalance("y")); // Output: true
        System.out.println(myClass.xyBalance("")); // Output: true
        System.out.println(myClass.xyBalance("xyxyxy")); // Output: true
        System.out.println(myClass.xyBalance("bbb")); // Output: true
    }

    public boolean xyBalance(String str) {
        // Iterate from the end of the string to the start
        boolean foundY = false; // Track if we've found a 'y'
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == 'y') {
                foundY = true; // Found a 'y'
            }
            if (str.charAt(i) == 'x') {
                if (!foundY) {
                    return false; // Found an 'x' without a 'y' after it
                }
            }
        }
        return true; // All 'x's have a 'y' after them
    }
}
