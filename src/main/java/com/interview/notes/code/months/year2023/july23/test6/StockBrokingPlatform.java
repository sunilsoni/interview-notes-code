package com.interview.notes.code.months.year2023.july23.test6;

import java.util.HashMap;
import java.util.Map;

/**
 * Concept: Stock Broking platform
 * User Accounts Each account will have multiple stocks with no. of shares
 * Ex: Account 1 Apple, 10 shares, price:900
 * Microsoft, 100 shares, price:400
 * Account 2 Google, 100 shares, price:100
 * Microsoft, 200 shares, price:400
 * Account 3 Google, 70 shares, price:100 Apple, 100 shares, price:900
 * Group by Account, calculate total price for each account and display
 */
public class StockBrokingPlatform {
    private Map<Integer, Account> accounts;

    public StockBrokingPlatform() {
        this.accounts = new HashMap<>();
    }

    public static void main(String[] args) {
        // Create stocks
        Stock appleStock1 = new Stock("Apple", 10, 900);
        Stock microsoftStock1 = new Stock("Microsoft", 100, 400);
        Stock googleStock1 = new Stock("Google", 100, 100);
        Stock microsoftStock2 = new Stock("Microsoft", 200, 400);
        Stock googleStock2 = new Stock("Google", 70, 100);
        Stock appleStock2 = new Stock("Apple", 100, 900);

        // Create accounts and add stocks to them
        Account account1 = new Account(1);
        account1.addStock(appleStock1);
        account1.addStock(microsoftStock1);

        Account account2 = new Account(2);
        account2.addStock(googleStock1);
        account2.addStock(microsoftStock2);

        Account account3 = new Account(3);
        account3.addStock(googleStock2);
        account3.addStock(appleStock2);

        // Create the Stock Broking Platform
        StockBrokingPlatform platform = new StockBrokingPlatform();
        platform.addAccount(account1);
        platform.addAccount(account2);
        platform.addAccount(account3);

        // Display total value for each account
        platform.displayTotalValueForEachAccount();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    // Add additional methods as needed

    public void displayTotalValueForEachAccount() {
        for (Account account : accounts.values()) {
            double totalValue = account.getTotalValue();
            System.out.println("Account " + account.getAccountId() + ", Total Value: " + totalValue);
        }
    }
}
