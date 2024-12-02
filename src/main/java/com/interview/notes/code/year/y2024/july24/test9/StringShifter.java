package com.interview.notes.code.year.y2024.july24.test9;

public class StringShifter {
    public static void main(String[] args) {
        String input = "krishnrajendra";
        int shiftNumber = 3;
        String output = shiftCharactersRight(input, shiftNumber);
        System.out.println(output); // Should print "shnrajendrakri"
    }

    public static String shiftCharactersRight(String input, int number) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        int shift = number % input.length();
        return input.substring(input.length() - shift) + input.substring(0, input.length() - shift);
    }
}
