package com.interview.notes.code.months.jan24.test8;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountId;
    private Double balance;
    private List<Account> childAccounts;

    // Constructor for the Account class
    public Account(String accountId) {
        this.accountId = accountId;
        this.childAccounts = new ArrayList<>();
        // Initially, we set balance to null to represent that it's not set for non-leaf accounts
        this.balance = null;
    }
}