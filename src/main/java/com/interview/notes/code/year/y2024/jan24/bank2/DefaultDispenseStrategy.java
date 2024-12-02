package com.interview.notes.code.year.y2024.jan24.bank2;

class DefaultDispenseStrategy implements CashDispenseStrategy {
    @Override
    public boolean dispenseCash(double amount, CashReserve cashReserve) {
        // Simplified logic for dispensing cash
        if (cashReserve.hasSufficientCash(amount)) {
            cashReserve.debit(amount);
            System.out.println("Dispensed: " + amount);
            return true;
        } else {
            System.out.println("Insufficient cash in ATM.");
            return false;
        }
    }
}