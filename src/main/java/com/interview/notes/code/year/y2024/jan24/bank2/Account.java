package com.interview.notes.code.year.y2024.jan24.bank2;


import com.interview.notes.code.year.y2024.jan24.bank.ATMException;

// Account class
class Account {
    private String accountNumber;
    private int pin;
    private double balance;

    public Account(String accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }
// Constructor, getters, and setters omitted for brevity

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean authenticate(int inputPin) {
        return this.pin == inputPin;
    }

    public void withdraw(double amount) throws ATMException {
        if (amount > balance) {
            throw new ATMException("Insufficient funds.");
        }
        balance -= amount;
    }
}