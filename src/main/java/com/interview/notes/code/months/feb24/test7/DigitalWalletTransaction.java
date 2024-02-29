package com.interview.notes.code.months.feb24.test7;

class DigitalWalletTransaction {

    public void addMoney(DigitalWallet digitalWallet, int amount) throws TransactionException {
        if (!digitalWallet.hasAccessCode()) {
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
        }
        if (amount <= 0) {
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
        }
        digitalWallet.setWalletBalance(digitalWallet.getBalance() + amount);
    }

    public void payMoney(DigitalWallet digitalWallet, int amount) throws TransactionException {
        if (!digitalWallet.hasAccessCode()) {
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
        }
        if (amount <= 0) {
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
        }
        if (digitalWallet.getBalance() < amount) {
            throw new TransactionException("Insufficient balance", "INSUFFICIENT_BALANCE");
        }
        digitalWallet.setWalletBalance(digitalWallet.getBalance() - amount);
    }
}