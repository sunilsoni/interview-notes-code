package com.interview.notes.code.months.july24.test7;

import java.util.Arrays;
import java.util.stream.IntStream;

public class NumberToWords {
    public static void main(String[] args) {
        // Create an array of integers from 1 to 9
        int[] numbers = IntStream.rangeClosed(1, 9).toArray();

        // Convert and print numbers greater than 3 in word format
        Arrays.stream(numbers)
              .filter(n -> n > 3) // Filter numbers greater than 3
              .mapToObj(NumberToWords::numberToWord) // Convert number to word
              .forEach(System.out::println); // Print each word
    }

    // Method to convert numbers to words
    private static String numberToWord(int number) {
        switch (number) {
            case 4: return "four";
            case 5: return "five";
            case 6: return "six";
            case 7: return "seven";
            case 8: return "eight";
            case 9: return "nine";
            default: return String.valueOf(number);
        }
    }
}
