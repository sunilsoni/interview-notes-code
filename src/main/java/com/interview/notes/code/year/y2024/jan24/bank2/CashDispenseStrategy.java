package com.interview.notes.code.year.y2024.jan24.bank2;

interface CashDispenseStrategy {
    boolean dispenseCash(double amount, CashReserve cashReserve);
}