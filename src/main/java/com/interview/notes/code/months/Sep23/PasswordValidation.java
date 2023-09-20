package com.interview.notes.code.months.Sep23;

import java.util.Scanner;

/**
 * Password Validation
 * Recommended Time: 10 min
 * Check whether a given string is a valid password or not.
 * A valid password is determined by the following rules:
 * It must have a minimum of eight characters.
 * It should consist of only letters or digits.
 * It must contain at least two digits.
 * You only need to update the code segment indicated.
 * Examples:
 * Input: abc123def
 * Output: Valid
 * Input: pass4wd
 * Output: Invalid
 * Constraints:
 * The output format should exactly match the examples shown above.
 */
public class PasswordValidation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        // BEGIN OF MISSING CODE SEGMENT
        boolean isValid = isValidPassword(password);
        if (isValid) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
        // END OF MISSING CODE SEGMENT
    }

    private static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        int digitCount = 0;
        for (char ch : password.toCharArray()) {
            if (!Character.isLetterOrDigit(ch)) {
                return false;
            }
            if (Character.isDigit(ch)) {
                digitCount++;
            }
        }
        return digitCount >= 2;
    }
}
