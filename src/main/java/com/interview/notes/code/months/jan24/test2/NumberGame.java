package com.interview.notes.code.months.jan24.test2;

public class NumberGame {
    public static int solve(String N1, String N2) {
        int steps = 0;
        for (int i = 0; i < N1.length(); i++) {
            if (N1.charAt(i) != N2.charAt(i)) {
                steps++;
            }
        }
        return steps;
    }

    // Example usage
    public static void main(String[] args) {
        String N1 = "1479";
        String N2 = "5263";
        System.out.println(solve(N1, N2)); // Output will be 4
    }
}
