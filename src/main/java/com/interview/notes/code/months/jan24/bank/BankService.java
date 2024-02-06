package com.interview.notes.code.months.jan24.bank;

// BankService class
class BankService {
    private AccountFactory accountFactory;

    public BankService(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    public Account getAccount(String accountNumber, int pin) throws ATMException {
        Account account = accountFactory.getAccount(accountNumber);
        if (account != null && account.authenticate(pin)) {
            return account;
        }
        throw new ATMException("Authentication failed.");
    }
}