package com.interview.notes.code.months.march24.test4;

public class Main {
    public static void main(String[] args) {
        String[] words = {"a", "z", "chase", "c", "chase", "d"};
        String targetWord = "chase"; // The word to be moved to the beginning

        moveToTheBeginning(words, targetWord);
        System.out.println(java.util.Arrays.toString(words));
    }

    private static void moveToTheBeginning(String[] array, String targetWord) {
        int targetCount = 0; // Counter for the targetWord occurrences
        // Count how many times targetWord appears
        for (String s : array) {
            if (s.equals(targetWord)) {
                targetCount++;
            }
        }

        // Fill the beginning of the array with targetWord
        int index = 0;
        while (targetCount > 0) {
            array[index++] = targetWord;
            targetCount--;
        }

        // Fill the rest of the array with non-targetWord elements
        for (String s : array) {
            if (!s.equals(targetWord)) {
                // Check if the current position is already filled with targetWord
                if (index < array.length && array[index] == targetWord) {
                    index++;
                }
                array[index++] = s;
            }
        }
    }
}
