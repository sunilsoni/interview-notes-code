package com.interview.notes.code.months.feb24.test1;

public class UniqueNumbersFinder1 {
    // Method to find unique numbers in an array
    public static int[] findUniqueNumbers(int[] input) {
        // Initial assumption: all numbers could be unique, hence max length of input
        int[] tempUnique = new int[input.length];
        int uniqueCount = 0;

        for (int i = 0; i < input.length; i++) {
            boolean isUnique = true;
            // Check each number for uniqueness
            for (int j = 0; j < input.length; j++) {
                // Skip self-comparison
                if (i != j && input[i] == input[j]) {
                    isUnique = false;
                    break;
                }
            }
            // If unique, add to the temporary array
            if (isUnique) {
                tempUnique[uniqueCount++] = input[i];
            }
        }

        // Copy unique numbers to the final array
        int[] uniqueNumbers = new int[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            uniqueNumbers[i] = tempUnique[i];
        }

        return uniqueNumbers;
    }

    // Main method for example execution
    public static void main(String[] args) {
        int[] input = {1, 2, 4, 2, 3, 5, 1, 6, 3, 8};
        int[] uniqueNumbers = findUniqueNumbers(input);
        System.out.println("Unique numbers in the array are:");
        for (int num : uniqueNumbers) {
            System.out.print(num + " ");
        }
    }
}
