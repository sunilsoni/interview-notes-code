package com.interview.notes.code.months.year2023.june23.test8;

public class CountStringCharacters {
    public static void main(String[] args) {
        String str = "examplestring";
        countCharacters(str);
    }

    static void countCharacters(String str) {
        // Assuming ASCII, create an array to hold counts for each character
        int[] count = new int[256];

        // For each character in the string
        for (int i = 0; i < str.length(); i++) {
            // Increment the count for this character
            count[str.charAt(i)]++;
        }

        // Now, print out the counts
        for (int i = 0; i < 256; i++) {
            // If this character is in the string
            if (count[i] > 0) {
                // Print its count
                System.out.println((char) i + ": " + count[i]);
            }
        }
    }
}
