package com.interview.notes.code.year.y2023.Oct23.test2;

class AdminPasswordValidator implements CommonValidation {
    public ValidationResult validate(String password) {
        // Common validations
        if (!hasMinimumLength(password, 12)) {
            return new ValidationResult(false, "Password must be at least 12 characters long");
        }
        if (!hasDigit(password)) {
            return new ValidationResult(false, "Password must contain at least one digit");
        }
        // Admin-specific validations
        return null;
    }
}
