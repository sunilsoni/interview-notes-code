package com.interview.notes.code.months.jan24.bank2;

interface CashDispenseStrategy {
    boolean dispenseCash(double amount, CashReserve cashReserve);
}