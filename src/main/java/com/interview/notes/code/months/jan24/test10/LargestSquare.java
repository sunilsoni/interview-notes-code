package com.interview.notes.code.months.jan24.test10;

public class LargestSquare {

    public static void main(String[] args) {
        int stick1 = 10;
        int stick2 = 21;

        Solution1 solution = new Solution1();
        int sideLength = solution.solution(stick1, stick2);

        System.out.println("Stick 1 length: " + stick1);
        System.out.println("Stick 2 length: " + stick2);
        System.out.println("Side length of the largest square: " + sideLength);
    }

    // Solution class with gcd method remains the same as before
}