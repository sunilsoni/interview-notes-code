package com.interview.notes.code.year.y2024.sept24.test3;

public class Main {
    private static final int[] HOLE_COUNTS = {1, 0, 0, 0, 1, 0, 1, 0, 2, 1};

    public static void main(String[] args) {
        // Test cases
        System.out.println("Test case 1: " + solve(2));
        System.out.println("Test case 2: " + solve(3));
        System.out.println("Test case 3: " + solve(10));
        System.out.println("Test case 4: " + solve(0));
        System.out.println("Test case 5: " + solve(100));
    }

    public static String solve(int K) {
        if (K == 0) return "1";
        if (K == 1) return "0";

        int number = 1;
        while (true) {
            if (countHoles(number) == K) {
                return Integer.toString(number);
            }
            number++;
        }
    }

    private static int countHoles(int number) {
        int holes = 0;
        while (number > 0) {
            holes += HOLE_COUNTS[number % 10];
            number /= 10;
        }
        return holes;
    }
}
