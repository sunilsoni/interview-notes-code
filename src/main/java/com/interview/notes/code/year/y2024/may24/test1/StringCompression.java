package com.interview.notes.code.year.y2024.may24.test1;

/**
 * Your team works on processing extremely long strings of sequences of repeated characters. One of your
 * teammates proposes that you can save memory by compressing the strings with Huffman code. Huffman code takes a
 * string ‘aaabbaaccc’ and returns ‘a3b2a2c3’. Write the pseudocode for a method that will determine if, for a provided
 * string, the Huffman code will be shorter than the original string
 */
public class StringCompression {

    // Function to determine if the compressed string is shorter
    public static boolean isCompressedShorter(String originalString) {
        // Step 2: Check for null or empty string
        if (originalString == null || originalString.length() == 0) {
            return false;
        }

        // Step 3: Initialize a StringBuilder for efficiency in appending characters
        StringBuilder encodedString = new StringBuilder();
        // Step 4: Start with a count of 1
        int count = 1;

        // Step 5: Iterate over the string, comparing each character to the next
        for (int i = 0; i < originalString.length() - 1; i++) {
            // Step 5a: If the current and next characters are the same, increment count
            if (originalString.charAt(i) == originalString.charAt(i + 1)) {
                count++;
            } else {
                // Step 5b: Append the character and its count to the encoded string
                encodedString.append(originalString.charAt(i));
                encodedString.append(count);
                // Reset count for the next new character
                count = 1;
            }
        }

        // Step 6: Handle the final character and its count
        encodedString.append(originalString.charAt(originalString.length() - 1));
        encodedString.append(count);

        // Step 7 and 8: Return true if the encoded string is shorter
        return encodedString.length() < originalString.length();
    }

    public static void main(String[] args) {
        // Test the function with a sample string
        String testString = "aaabbaaccc";
        boolean result = isCompressedShorter(testString);
        System.out.println("Is the compressed string shorter? " + result);
    }
}
