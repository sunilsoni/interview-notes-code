package com.interview.notes.code.months.july23.test12;

public class FizzBuzz {
    public static void main(String[] args) {
        // Loop through numbers from 1 to 100
        for (int i = 1; i <= 100; i++) {
            // Check for multiples of both 3 and 5 first, as they must print "FizzBuzz"
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.print("FizzBuzz");
            }
            // If it's only a multiple of 3, print "Fizz"
            else if (i % 3 == 0) {
                System.out.print("Fizz");
            }
            // If it's only a multiple of 5, print "Buzz"
            else if (i % 5 == 0) {
                System.out.print("Buzz");
            }
            // Otherwise, print the number itself
            else {
                System.out.print(i);
            }

            // Separate the numbers by a comma and space, except for the last one
            if (i < 100) {
                System.out.print(", ");
            }
        }
    }
}

