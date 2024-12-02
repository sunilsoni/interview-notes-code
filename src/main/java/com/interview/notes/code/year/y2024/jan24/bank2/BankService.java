package com.interview.notes.code.year.y2024.jan24.bank2;

class BankService implements ATMObserver {
    // Rest of the BankService class...

    @Override
    public void update(String eventType, double data) {
        if ("WITHDRAWAL".equals(eventType)) {
            // Handle withdrawal notification
            System.out.println("Bank Service: Withdrawal of " + data + " processed.");
        } else if ("BALANCE_CHANGE".equals(eventType)) {
            // Handle balance change notification
            System.out.println("Bank Service: Account balance changed by " + data + ".");
        }
    }
}