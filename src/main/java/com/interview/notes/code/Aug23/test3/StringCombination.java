package com.interview.notes.code.Aug23.test3;

public class StringCombination {
    public static void main(String[] args) {
        // The input string
        String str = "abc";
        // Length of the combination
        int length = 3;

        // Start the combination process
        generateCombinations(str, "", 0, length);
    }

    /**
     * A recursive function to generate combinations.
     *
     * @param str    The input string
     * @param result The current combination being built
     * @param index  The current index in the input string
     * @param length The length of the combination to generate
     */
    public static void generateCombinations(String str, String result, int index, int length) {
        // If the length of the result string is equal to the given length,
        // print the result
        if (result.length() == length) {
            System.out.println(result);
            return;
        }

        // If the index exceeds the string length, return
        if (index == str.length()) {
            return;
        }

        // Include the current character in the result and move to the next index
        generateCombinations(str, result + str.charAt(index), index + 1, length);

        // Exclude the current character and move to the next index
        generateCombinations(str, result, index + 1, length);
    }
}
