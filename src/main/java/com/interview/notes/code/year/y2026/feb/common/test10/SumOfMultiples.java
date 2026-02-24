package com.interview.notes.code.year.y2026.feb.common.test10;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SumOfMultiples {           // Main class

    // Method to calculate sum of multiples
    static int sum(int[] a, int d) {    // a = array, d = divisor
        return Arrays.stream(a)         // Convert array to IntStream
                .filter(i -> i % d == 0)// Keep only numbers divisible by d
                .sum();                 // Add remaining numbers
    }

    public static void main(String[] args) { // Main method

        int[] a = IntStream.rangeClosed(1, 20) // Create numbers 1 to 20
                .toArray();                    // Convert stream to array

        // Expected values (manual calculation)
        int e2 = 110;  // 2+4+6+8+10+12+14+16+18+20
        int e3 = 63;   // 3+6+9+12+15+18
        int e4 = 60;   // 4+8+12+16+20
        int e5 = 50;   // 5+10+15+20

        // Actual calculation
        int r2 = sum(a,2);   // Sum of multiples of 2
        int r3 = sum(a,3);   // Sum of multiples of 3
        int r4 = sum(a,4);   // Sum of multiples of 4
        int r5 = sum(a,5);   // Sum of multiples of 5

        // Print results
        System.out.println("Sum of Multiples of 2 : " + r2);
        System.out.println("Sum of Multiples of 3 : " + r3);
        System.out.println("Sum of Multiples of 4 : " + r4);
        System.out.println("Sum of Multiples of 5 : " + r5);

        // PASS / FAIL validation
        System.out.println("Test 2 : " + (r2==e2?"PASS":"FAIL"));
        System.out.println("Test 3 : " + (r3==e3?"PASS":"FAIL"));
        System.out.println("Test 4 : " + (r4==e4?"PASS":"FAIL"));
        System.out.println("Test 5 : " + (r5==e5?"PASS":"FAIL"));

        // Large data test (1 to 1,000,000)
        int[] big = IntStream.rangeClosed(1,1_000_000).toArray();
        System.out.println("Large Test (2) : " + sum(big,2));
    }
}