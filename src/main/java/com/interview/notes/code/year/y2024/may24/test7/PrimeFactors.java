package com.interview.notes.code.year.y2024.may24.test7;

//Find the prime factors of a number. e.g 1,2,7 are the prime factors of 14.
public class PrimeFactors {
    public static void main(String[] args) {
        int number = 14;  // You can change this number to test other values
        System.out.println("Prime factors of " + number + " are:");

        // Print the number of 2s that divide the number
        while (number % 2 == 0) {
            System.out.print(2 + " ");
            number /= 2;
        }

        // Number must be odd at this point so we can skip even numbers
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            // While i divides number, print i and divide number
            while (number % i == 0) {
                System.out.print(i + " ");
                number /= i;
            }
        }

        // This condition is to handle the case when number is a prime number
        // greater than 2
        if (number > 2) {
            System.out.print(number);
        }
    }
}
