package com.interview.notes.code.months.jan24.bank2;

import com.interview.notes.code.months.jan24.bank.ATM;
import com.interview.notes.code.months.jan24.bank.BankService;

public class ATMApplication {
    public static void main(String[] args) {
        ATM atm = ATM.getInstance();
        BankService bankService = new BankService();
        atm.addObserver(bankService);
       // atm.start();
    }
}