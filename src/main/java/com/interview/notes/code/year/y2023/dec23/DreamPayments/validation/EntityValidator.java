package com.interview.notes.code.year.y2023.dec23.DreamPayments.validation;

public interface EntityValidator {
    // TODO: Implement this method.
    // This method should validate a payee verification code by checking if the sum of all
    // digits of a payee's verification code are less than 30.
    // If it is, it is a valid verification code. If not, it is invalid.
    // Example: 12341234 is considered a valid verification code as its total sum of digits is less than 30.
    // Example: 99999999 is considered an invalid verification code is its total sum of digits is greater than 30.
    boolean validateEntityVerificationCode(int verificationCode);
    // TODO: Implement this method.
    // This method should validate a business verification code by checking if the sum of all
    // digits of a payee's verification code are less than 30.
    // If it is, it is a valid verification code. If not, it is invalid.
    // Example: 12341234 is considered a valid verification code as its total sum of digits is less than 30.
    // Example: 99999999 is considered an invalid verification code is its total sum of digits is greater than 30.

    // However, if the business is a foreign business, then the verification code criteria is as follows.
    // The sum of all digits of the verification code must be greater or equal than 30.
    // Every even digit (using the rightmost digit as the first digit) of the verification code must be an odd number.
    // If both of these criteria are met, it is a valid verification code. If not, it is invalid.
    // Example: 98987852 is considered a valid verification code
    // as its total sum of digits is greater than or equal to 30.
    // Note that the first digit is 2, not 9 as the first digit is the rightmost digit, instead of leftmost.
    // Thus, the second digit is 5, third digit is 8, fourth digit is 7, and so on.
    // Example: 30303030 is considered an invalid verification code is its total sum of digits is less than 30.
    // Example 98989828 is considered an invalid verification code as its second digit, 2, is an even number, not odd.

    boolean validateEntityVerificationCode(int verificationCode, boolean foreignBusiness);
}
