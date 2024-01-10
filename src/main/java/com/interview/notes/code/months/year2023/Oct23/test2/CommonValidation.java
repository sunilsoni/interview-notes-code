package com.interview.notes.code.months.year2023.Oct23.test2;

/**
 * Write a password validator class that given a user password will validate the password
 * and return false if the password is invalid.
 * <p>
 * Password Validation:
 * The length must be greater than 8 characters
 * <p>
 * In addition to the Boolean, modify your password validator to return a reason for failure should the password be invalid.
 * <p>
 * <p>
 * The validation rules have now changed. Update your code to include the following rules.
 * <p>
 * Rules
 * 1. Must contain at least one numeric character
 * 2. Must contain at least one uppercase character
 */
interface CommonValidation {
    default boolean hasMinimumLength(String password, int minLength) {
        return password.length() >= minLength;
    }

    default boolean hasDigit(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }
}