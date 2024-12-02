package com.interview.notes.code.year.y2024.april24.test14;

public class StringCounter {
    public static void main(String[] args) {
        String[] strings = {"AAA", "BBB", "CCC", "ABC", "AAA", "AAA", "ABC"};

        // Count occurrences of each string
        int[] counts = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                if (strings[i].equals(strings[j])) {
                    counts[i]++;
                }
            }
        }

        // Print strings and their counts
        for (int i = 0; i < strings.length; i++) {
            // Check if the string has already been printed
            boolean printed = false;
            for (int j = 0; j < i; j++) {
                if (strings[i].equals(strings[j])) {
                    printed = true;
                    break;
                }
            }
            if (!printed) {
                System.out.println(strings[i] + " - " + counts[i]);
            }
        }
    }
}
