package com.interview.notes.code.months.year2023.dec23.DreamPayments.model;

public enum FailureReason {

    TERMINATED_ENTITY("PF001", "Entity status is TERMINATED."),
    ILLEGAL_AGE("PF002", "Payee is not at the valid age."),
    ILLEGAL_VERIFICATION_CODE("PF003", "Entity possesses an illegal verification code.");

    public final String errorCode;
    public final String failureReason;

    FailureReason(String errorCode, String failureReason) {
        this.errorCode = errorCode;
        this.failureReason = failureReason;
    }
}
