package com.interview.notes.code.year.y2023.Oct23.test2;

import java.util.Arrays;

public class KaprekarMagicNumber {
    public static void main(String[] args) {
        int count = findStepsToReach6174();
        System.out.println("It takes " + count + " steps to reach 6174 for all four-digit numbers.");
    }

    public static int findStepsToReach6174() {
        int count = 0;
        for (int num = 1000; num <= 9999; num++) {
            if (num == 6174) {
                continue; // Skip the magic number itself
            }

            int result = num;
            while (result != 6174 && result != 0) {
                int ascending = getAscending(result);
                int descending = getDescending(result);
                result = descending - ascending;
                count++;

                if (result == 0) {
                    break; // Exception case for all same digits (e.g., 1111, 2222)
                }
            }
        }
        return count;
    }

    public static int getAscending(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        Arrays.sort(digits);
        return Integer.parseInt(new String(digits));
    }

    public static int getDescending(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        Arrays.sort(digits);
        StringBuilder sb = new StringBuilder(new String(digits));
        return Integer.parseInt(sb.reverse().toString());
    }
}
