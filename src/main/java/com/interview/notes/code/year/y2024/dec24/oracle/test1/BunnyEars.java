package com.interview.notes.code.year.y2024.dec24.oracle.test1;

public class BunnyEars {

    // Main method to test the bunnyEars function
    public static void main(String[] args) {
        BunnyEars bunnyEarsObj = new BunnyEars();

        // Test cases
        System.out.println("bunnyEars(0) = " + bunnyEarsObj.bunnyEars(0)); // Expected output: 0
        System.out.println("bunnyEars(1) = " + bunnyEarsObj.bunnyEars(1)); // Expected output: 2
        System.out.println("bunnyEars(2) = " + bunnyEarsObj.bunnyEars(2)); // Expected output: 4
        System.out.println("bunnyEars(3) = " + bunnyEarsObj.bunnyEars(3)); // Expected output: 6
        System.out.println("bunnyEars(4) = " + bunnyEarsObj.bunnyEars(4)); // Expected output: 8
    }

    // Recursive method to calculate the total number of ears
    public int bunnyEars(int bunnies) {
        // Base case: If there are no bunnies, return 0 ears
        if (bunnies == 0) {
            return 0;
        } else {
            // Recursive case: 2 ears for the current bunny + ears for the remaining bunnies
            return 2 + bunnyEars(bunnies - 1);
        }
    }
}
