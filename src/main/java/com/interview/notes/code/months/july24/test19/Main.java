package com.interview.notes.code.months.july24.test19;

public class Main {
    public static String firstQuarter(int N, String[] S) {
        String result = S[0];  // Initialize with the first quarter

        for (int i = 1; i < N; i++) {
            if (compareQuarters(S[i], result) < 0) {
                result = S[i];
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
        String[] test1 = {"2Q22", "1Q23", "1Q22"};
        System.out.println(firstQuarter(3, test1));  // Expected: 1Q22

        // Test case 2
        String[] test2 = {"4Q99", "1Q00", "2Q00"};
        System.out.println(firstQuarter(3, test2));  // Expected: 1Q00

        // Test case 3
        String[] test3 = {"1Q01", "2Q01", "3Q01", "4Q01"};
        System.out.println(firstQuarter(4, test3));  // Expected: 1Q01

        // Test case 4
        String[] test4 = {"3Q50", "1Q50", "4Q49", "2Q51"};
        System.out.println(firstQuarter(4, test4));  // Expected: 4Q49

        // Test case 5
        String[] test5 = {"1Q00"};
        System.out.println(firstQuarter(1, test5));  // Expected: 1Q00
    }
}
