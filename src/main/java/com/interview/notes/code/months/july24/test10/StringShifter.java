package com.interview.notes.code.months.july24.test10;


public class StringShifter {
    public static void main(String[] args) {
        String input = "krishnrajendra";
        int shiftNumber = 3;
        String output = shiftCharactersLeft(input, shiftNumber);
        System.out.println(output); // Should print "shnrajendrakri"
    }

    public static String shiftCharactersLeft(String input, int number) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        int shift = number % input.length();
        // To perform a left shift, we take the substring from 'shift' to the end of the string
        // and concatenate it to the beginning of the substring from 0 to 'shift'.
        return input.substring(shift) + input.substring(0, shift);
    }
}
