package com.interview.notes.code.months.jan24.bank2;


import com.interview.notes.code.months.jan24.bank.AccountFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private static ATM instance = new ATM();
    private List<ATMObserver> observers = new ArrayList<>();
    private BankService bankService;
    private Account currentAccount;
    private Scanner scanner;

    private ATM() {
        AccountFactory accountFactory = new AccountFactory();
        //this.bankService = new  BankService(accountFactory);
        this.scanner = new Scanner(System.in);
    }

    public static ATM getInstance() {
        return instance;
    }

    public void addObserver(ATMObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String eventType, double data) {
        for (ATMObserver observer : observers) {
            observer.update(eventType, data);
        }
    }

    private void performTransaction() {
        // Transaction logic...
        // After a successful withdrawal
        // notifyObservers("WITHDRAWAL", amount);
        //notifyObservers("BALANCE_CHANGE", -amount);
    }
}