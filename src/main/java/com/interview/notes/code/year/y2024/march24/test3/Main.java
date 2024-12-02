package com.interview.notes.code.year.y2024.march24.test3;

public class Main {
    public static void main(String[] args) {
        String[] words = {"a", "z", "chase", "c", "chase", "d"};
        String targetWord = "chase"; // The word to be moved to the end

        moveToTheEnd(words, targetWord);
        System.out.println(java.util.Arrays.toString(words));
    }

    private static void moveToTheEnd(String[] array, String targetWord) {
        int count = 0; // Counter for non-targetWord elements
        // Temporarily store elements that are not targetWord
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(targetWord)) {
                array[count++] = array[i];
            }
        }
        // Fill the rest of the array with targetWord
        while (count < array.length) {
            array[count++] = targetWord;
        }
    }
}
