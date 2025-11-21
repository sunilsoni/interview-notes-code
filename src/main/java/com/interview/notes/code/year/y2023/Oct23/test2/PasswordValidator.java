package com.interview.notes.code.year.y2023.Oct23.test2;

class PasswordValidator {
    private final PasswordValidationStrategy strategy;

    public PasswordValidator(PasswordValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public ValidationResult validate(String password) {
        return strategy.validate(password);
    }
}