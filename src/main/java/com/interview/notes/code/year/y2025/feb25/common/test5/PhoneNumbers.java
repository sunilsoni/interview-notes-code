package com.interview.notes.code.year.y2025.feb25.common.test5;

import java.util.*;

public class PhoneNumbers {
    public static void generatePhoneNumbers(int length, Set<Integer> disallowedDigits) {
        List<String> validNumbers = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        generateValidNumbers(current, length, disallowedDigits, validNumbers);

        // Sort and print results
        Collections.sort(validNumbers);
        for (String number : validNumbers) {
            System.out.println(number);
        }
    }

    private static void generateValidNumbers(StringBuilder current, int length,
                                             Set<Integer> disallowedDigits,
                                             List<String> validNumbers) {
        if (current.length() == length) {
            validNumbers.add(current.toString());
            return;
        }

        for (int digit = 0; digit <= 9; digit++) {
            if (disallowedDigits.contains(digit)) {
                continue;
            }

            // Check if 4 appears at the start
            if (digit == 4 && current.length() == 0) {
                continue;
            }

            // Check if number containing 4 starts with 4
            if (digit == 4 && !current.toString().startsWith("4")) {
                continue;
            }

            // Check for repeated digits
            if (current.length() > 0 &&
                    digit == Character.getNumericValue(current.charAt(current.length() - 1))) {
                continue;
            }

            current.append(digit);
            generateValidNumbers(current, length, disallowedDigits, validNumbers);
            current.setLength(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println("Test 1: Length 3, Disallowed: [1, 5, 7]");
        Set<Integer> disallowed1 = new HashSet<>(Arrays.asList(1, 5, 7));
        generatePhoneNumbers(3, disallowed1);

        System.out.println("\nTest 2: Length 4, Disallowed: [0, 8]");
        Set<Integer> disallowed2 = new HashSet<>(Arrays.asList(0, 8));
        generatePhoneNumbers(4, disallowed2);
    }
}
