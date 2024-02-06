package com.interview.notes.code.months.jan24.bank2;

class CashReserve {
    private double totalCash;

    public CashReserve(double initialCash) {
        this.totalCash = initialCash;
    }

    public boolean hasSufficientCash(double amount) {
        return totalCash >= amount;
    }

    public void debit(double amount) {
        if (hasSufficientCash(amount)) {
            totalCash -= amount;
        }
    }

    // Additional methods to add cash, get total cash, etc.
}