package com.interview.notes.code.Aug23.test5;

import java.util.Arrays;

public class KaprekarConstant {

    public static void main(String[] args) {
        for (int i = 1000; i <= 9999; i++) {
            int steps = findStepsToKaprekar(i);
            if (steps != -1) {
                System.out.println("Number: " + i + ", Steps: " + steps);
            } else {
                System.out.println("Number: " + i + " can't reach 6174.");
            }
        }
    }

    public static int findStepsToKaprekar(int num) {
        if (allSameDigits(num)) {
            return -1;  // Can't reach 6174
        }

        int steps = 0;
        while (num != 6174) {
            num = kaprekarOperation(num);
            steps++;
        }
        return steps;
    }

    private static int kaprekarOperation(int num) {
        int asc = Integer.parseInt(ascendingOrder(num));
        int desc = Integer.parseInt(descendingOrder(num));
        return desc - asc;
    }

    private static String ascendingOrder(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }

    private static String descendingOrder(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        Arrays.sort(digits);
        return new StringBuilder(new String(digits)).reverse().toString();
    }

    private static boolean allSameDigits(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        char firstDigit = digits[0];
        for (char digit : digits) {
            if (digit != firstDigit) {
                return false;
            }
        }
        return true;
    }
}
