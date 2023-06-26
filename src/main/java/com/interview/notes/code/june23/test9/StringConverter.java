package com.interview.notes.code.june23.test9;

import java.util.Scanner;

public class StringConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input: ");
        String input = scanner.nextLine();

        String output = convertToUpperCaseWithUnderscores(input);

        System.out.println("Output: " + output);
    }

    private static String convertToUpperCaseWithUnderscores(String input) {
        // Replace spaces with underscores and convert to uppercase
        String convertedString = input.replaceAll(" ", "_").toUpperCase();

        return convertedString;
    }
}
