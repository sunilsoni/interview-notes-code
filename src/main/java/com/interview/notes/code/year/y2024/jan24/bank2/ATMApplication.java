package com.interview.notes.code.year.y2024.jan24.bank2;


public class ATMApplication {
    public static void main(String[] args) {
        ATM atm = ATM.getInstance();
        BankService bankService = new BankService();
        atm.addObserver(bankService);
        // atm.start();
    }
}