package com.interview.notes.code.months.Oct23.test2;

public class ValidationResult {
    private boolean isValid;
    private String failureReason;

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