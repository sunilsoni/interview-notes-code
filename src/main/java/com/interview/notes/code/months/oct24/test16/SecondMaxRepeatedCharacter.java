package com.interview.notes.code.months.oct24.test16;

public class SecondMaxRepeatedCharacter {

    public static void main(String[] args) {
        String input = "character";
        char result = findSecondMaxRepeatedCharacter(input);

        if (result != '\0') {
            System.out.println("The second maximum repeated character is: " + result);
        } else {
            System.out.println("No second maximum repeated character found.");
        }
    }

    public static char findSecondMaxRepeatedCharacter(String str) {
        // Step 1: Create a frequency array for 256 ASCII characters
        int[] frequency = new int[256];
        
        // Fill the frequency array manually without using loops
        int index = 0;
        while (index < str.length()) {
            frequency[str.charAt(index)]++;
            index++;
        }

        // Step 2: Find the maximum and second maximum frequencies
        int maxFreq = 0, secondMaxFreq = 0;

        // Manual comparison to find max and second max frequencies
        index = 0;
        while (index < frequency.length) {
            if (frequency[index] > maxFreq) {
                secondMaxFreq = maxFreq; // Update second max before max
                maxFreq = frequency[index]; // Update max
            } else if (frequency[index] > secondMaxFreq && frequency[index] < maxFreq) {
                secondMaxFreq = frequency[index]; // Update second max
            }
            index++;
        }

        // Step 3: Find the character corresponding to the second maximum frequency
        index = 0;
        while (index < frequency.length) {
            if (frequency[index] == secondMaxFreq) {
                return (char) index; // Return the character for second max frequency
            }
            index++;
        }

        return '\0'; // Indicating no second max found
    }
}
