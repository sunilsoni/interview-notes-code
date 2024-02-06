package com.interview.notes.code.months.jan24.bank;

// Application entry point
public class ATMApplication {
    public static void main(String[] args) {
        ATM atm = ATM.getInstance();
        atm.start();
    }
}