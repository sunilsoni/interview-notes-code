package com.interview.notes.code.months.jan24.test1;

public class Solution {

    public static long getCountOfSantiagoNumbers(long L, long R) {
        // Initialize the count of Santiago numbers to 0
        long count = 0;

        // Iterate through numbers starting with 1 and increasing until they are less than or equal to R
        for (long num = 1; num <= R; num *= 10) {
            // Calculate the next Santiago number in the sequence
            long next = num;

            // Keep increasing the next Santiago number until it's greater than R
            while (next <= R) {
                if (next >= L) {
                    // If the next Santiago number is within the range [L, R], increment the count
                    count++;
                }
                // Multiply the next Santiago number by 10 to move to the next digit
                next *= 10;
                // Add 1 to the next Santiago number to get the next candidate
                next++;
            }
        }

        // Return the total count of Santiago numbers in the range [L, R]
        return count;
    }

    public static void main(String[] args) {

    }
}
