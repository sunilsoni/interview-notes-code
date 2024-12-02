package com.interview.notes.code.year.y2024.march24.test1;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {10, 5, 20, 8, 15};

        // Initialize max with the first element of the array
        int max = numbers[0];

        // Iterate through the array starting from the second element
        for (int i = 1; i < numbers.length; i++) {
            // If the current element is greater than max, update max
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        // Print the maximum value
        System.out.println("The largest number in the array is: " + max);
    }
}
