package com.interview.notes.code.year.y2023.Oct23.test2;

public class ValidationResult {
    private final boolean isValid;
    private final String failureReason;

    public ValidationResult(boolean isValid, String failureReason) {
        this.isValid = isValid;
        this.failureReason = failureReason;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getFailureReason() {
        return failureReason;
    }
}