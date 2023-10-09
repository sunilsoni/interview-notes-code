package com.interview.notes.code.months.Oct23.test2;

class UserPasswordValidator implements CommonValidation {
    public ValidationResult validate(String password) {
        // Common validations
        if (!hasMinimumLength(password, 8)) {
            return new ValidationResult(false, "Password must be at least 8 characters long");
        }
        if (!hasDigit(password)) {
            return new ValidationResult(false, "Password must contain at least one digit");
        }
        // User-specific validations
        return null;
    }
}