package com.interview.notes.code.year.y2024.july24.test18;

public class Main {
    public static String firstQuarter(int N, String S) {
        // Split the input string into an array of quarters
        String[] quarters = S.split(" ");

        // Initialize result with the first quarter
        String result = quarters[0];

        // Compare each quarter with the current minimum
        for (int i = 1; i < N; i++) {
            if (compareQuarters(quarters[i], result) < 0) {
                result = quarters[i];
            }
        }

        return result;
    }

    private static int compareQuarters(String q1, String q2) {
        int year1 = Integer.parseInt(q1.substring(2));
        int year2 = Integer.parseInt(q2.substring(2));

        if (year1 != year2) {
            return year1 - year2;
        }

        int quarter1 = q1.charAt(0) - '0';
        int quarter2 = q2.charAt(0) - '0';

        return quarter1 - quarter2;
    }


    public static void main(String[] args) {
        // Test case 1
        System.out.println(firstQuarter(3, "2Q22 1Q23 1Q22"));  // Expected: 1Q22

        // Test case 2
        System.out.println(firstQuarter(3, "4Q99 1Q00 2Q00"));  // Expected: 1Q00

        // Test case 3
        System.out.println(firstQuarter(4, "1Q01 2Q01 3Q01 4Q01"));  // Expected: 1Q01

        // Test case 4
        System.out.println(firstQuarter(4, "3Q50 1Q50 4Q49 2Q51"));  // Expected: 4Q49

        // Test case 5
        System.out.println(firstQuarter(1, "1Q00"));  // Expected: 1Q00
    }
}
