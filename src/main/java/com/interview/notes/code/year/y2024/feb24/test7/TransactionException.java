package com.interview.notes.code.year.y2024.feb24.test7;

class TransactionException extends Exception {
    private String errorCode;

    public TransactionException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}