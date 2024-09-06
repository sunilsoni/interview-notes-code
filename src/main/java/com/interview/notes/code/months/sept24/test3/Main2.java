package com.interview.notes.code.months.sept24.test3;

import java.util.HashMap;
import java.util.Map;

/*
Holes In Numbers
You and your friend love playing with numbers. You challenge your friend to find the minimum positive integer with K holes in it and print the output as a string. A digit of an integer has a hole if it encloses an area within its written representation.
For example, Integers 0 and 4 have a hole, and 8 has two holes. Similarly, integer 123 has no holes.
Integers 0, 4, and 14 have one hole. Integers 44 and 18 have two holes.
Can you help your friend to solve this challenge?
Input
The input contains an integer K, representing the number of required holes.
Output
Print the minimum positive integer with K holes in it as a string.
Example #1
Input
2
Output
8
Explanation: 8 is the smallest positive integer with two holes in it.
 */
public class Main2 {
    public static void main(String[] args) {
        // Test cases
        System.out.println("Test Case 1: " + solve(2));
        System.out.println("Test Case 2: " + solve(3));
        System.out.println("Test Case 3: " + solve(0));
        System.out.println("Test Case 4: " + solve(10));
    }

    public static String solve(int K) {
        // Edge case: if K is 0, return "1" as it's the smallest positive integer with no holes
        if (K == 0) {
            return "1";
        }

        // Create a mapping of digits to their hole counts
        Map<Integer, Integer> digitHoles = new HashMap<>();
        digitHoles.put(0, 1);
        digitHoles.put(4, 1);
        digitHoles.put(6, 1);
        digitHoles.put(8, 2);

        int number = 1;
        while (true) {
            int totalHoles = countHoles(number, digitHoles);
            if (totalHoles == K) {
                return Integer.toString(number);
            }
            number++;
        }
    }

    private static int countHoles(int number, Map<Integer, Integer> digitHoles) {
        int totalHoles = 0;
        while (number > 0) {
            int digit = number % 10;
            totalHoles += digitHoles.getOrDefault(digit, 0);
            number /= 10;
        }
        return totalHoles;
    }
}
