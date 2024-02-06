package com.interview.notes.code.months.jan24.bank;

// Exception class for handling ATM related errors
public class ATMException extends Exception {
    public ATMException(String message) {
        super(message);
    }
}