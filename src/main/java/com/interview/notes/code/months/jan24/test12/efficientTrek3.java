package com.interview.notes.code.months.jan24.test12;// Import Arrays class for sorting
import java.util.Arrays;

public class efficientTrek3 {

    // Define the function
    public static int efficientTrek(int[] trails, int record) {
        // Initialize the sum variable
        int sum = 0;

        // Sort the trails array in ascending order
        Arrays.sort(trails);

        // Loop from the end of the array to the record index
        for (int i = trails.length - 1; i >= trails.length - record; i--) {
            // Add the current trail length to the sum
            sum += trails[i];
        }

        // Return the sum
        return sum;
    }

    // Test the function with sample inputs
    public static void main(String[] args) {
        // Sample case 0
        int[] trails0 = {150, 200, 400, 350, 250};
        int record0 = 3;
        System.out.println(efficientTrek(trails0, record0)); // Output: 750

        // Sample case 1
        int[] trails1 = {78, 45, 12, 56, 85, 45};
        int record1 = 1;
        System.out.println(efficientTrek(trails1, record1)); // Output: 85
    }
}
