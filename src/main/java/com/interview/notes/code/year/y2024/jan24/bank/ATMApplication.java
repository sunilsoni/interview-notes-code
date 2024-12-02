package com.interview.notes.code.year.y2024.jan24.bank;

// Application entry point
public class ATMApplication {
    public static void main(String[] args) {
        ATM atm = ATM.getInstance();
        atm.start();
    }
}