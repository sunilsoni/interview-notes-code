package com.interview.notes.code.months.jan24.test9;

import java.util.Scanner;

public class FormattedNumber {
    public static String FormattedNumber(String[] strArr) {
        String number = strArr[0];
        // Remove commas for parsing
        number = number.replaceAll(",", "");
        try {
            // Check if it is a valid number
            Double.parseDouble(number);
            return "true";
        } catch (NumberFormatException e) {
            return "false";
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print(FormattedNumber(new String[]{s.nextLine()}));
    }
}
