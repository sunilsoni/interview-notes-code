package com.interview.notes.code.months.june23.test9;

import java.util.Scanner;

public class NameConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input whatIsYourName: ");
        String input = scanner.nextLine();

        String output = convertToUpperCaseWithUnderscores(input);

        System.out.println("Output: " + output);
    }

    private static String convertToUpperCaseWithUnderscores(String input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                sb.append('_');
            }

            sb.append(Character.toUpperCase(currentChar));
        }

        return sb.toString();
    }
}
