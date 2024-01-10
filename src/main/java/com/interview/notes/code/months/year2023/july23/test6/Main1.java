package com.interview.notes.code.months.year2023.july23.test6;

public class Main1 {
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
}
