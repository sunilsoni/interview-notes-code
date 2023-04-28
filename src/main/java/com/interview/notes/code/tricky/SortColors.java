package com.interview.notes.code.tricky;

import java.util.Arrays;
import java.util.Comparator;

public class SortColors {
    public static void main(String[] args) {
        String[] colors = {"red", "blue", "green"};

        // Define a Comparator for sorting colors in red-blue-green order
        Comparator<String> colorComparator = new Comparator<String>() {
            @Override
            public int compare(String color1, String color2) {
                switch (color1) {
                    case "red":
                        return color2.equals("red") ? 0 : -1;
                    case "blue":
                        return color2.equals("red") ? 1
                                : color2.equals("blue") ? 0 : -1;
                    case "green":
                        return color2.equals("green") ? 0 : 1;
                    default:
                        throw new IllegalArgumentException("Invalid color: " + color1);
                }
            }
        };

        // Sort colors using the Comparator
        Arrays.sort(colors, colorComparator);

        // Print the sorted colors
        for (String color : colors) {
            System.out.println(color);
        }
    }
}