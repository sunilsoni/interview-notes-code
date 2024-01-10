package com.interview.notes.code.months.year2023.Oct23.test2;

class PasswordValidator {
    private PasswordValidationStrategy strategy;

    public PasswordValidator(PasswordValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public ValidationResult validate(String password) {
        return strategy.validate(password);
    }
}