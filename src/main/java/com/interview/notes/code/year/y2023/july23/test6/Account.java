package com.interview.notes.code.year.y2023.july23.test6;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final int accountId;
    private final List<Stock> stocks;

    public Account(int accountId) {
        this.accountId = accountId;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double totalValue = 0.0;
        for (Stock stock : stocks) {
            totalValue += stock.getTotalValue();
        }
        return totalValue;
    }

    public Integer getAccountId() {
        return this.accountId;
    }

    // Add getters and setters if needed
}
