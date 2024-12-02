package com.interview.notes.code.year.y2024.sept24.test3;

import java.util.HashMap;
import java.util.Map;

public class Main3 {

    // Map for hole count per digit
    static Map<Character, Integer> holeCount = new HashMap<>();

    static {
        holeCount.put('0', 1);
        holeCount.put('1', 0);
        holeCount.put('2', 0);
        holeCount.put('3', 0);
        holeCount.put('4', 1);
        holeCount.put('5', 0);
        holeCount.put('6', 1);
        holeCount.put('7', 0);
        holeCount.put('8', 2);
        holeCount.put('9', 1);
    }

    public static void main(String[] args) {
        // Test the solve function with example inputs
        System.out.println(solve(2)); // Expected output: 8
        System.out.println(solve(3)); // Expected output: 48
    }

    public static String solve(int K) {
        int num = 1;

        // Iterate over numbers starting from 1
        while (true) {
            if (countHoles(Integer.toString(num)) == K) {
                return Integer.toString(num);
            }
            num++;
        }
    }

    // Method to count the number of holes in a given number
    public static int countHoles(String number) {
        int holes = 0;
        for (char digit : number.toCharArray()) {
            holes += holeCount.get(digit);
        }
        return holes;
    }
}
