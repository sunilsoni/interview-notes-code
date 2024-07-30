package com.interview.notes.code.months.july24.test11;

public class Main1 {
    public static void main(String[] args) {
        String s = "I Am Not String";
        String reversed = reverseStringPreserveSpaces(s);
        System.out.println(reversed); // Output: "g ni rtS toNmAI"
    }

    public static String reverseStringPreserveSpaces(String s) {
        char[] original = s.toCharArray();
        char[] reversed = new char[original.length];

        // Fill the reversed array with spaces where they are in the original array
        for (int i = 0; i < original.length; i++) {
            if (original[i] == ' ') {
                reversed[i] = ' ';
            }
        }

        // Iterate over the original string in reverse order
        int j = original.length - 1;
        for (char c : original) {
            // If the character is not a space, find the next non-space position in the reversed array
            if (c != ' ') {
                while (reversed[j] == ' ') {
                    j--;
                }
                reversed[j] = c;
                j--;
            }
        }

        return new String(reversed);
    }
}
