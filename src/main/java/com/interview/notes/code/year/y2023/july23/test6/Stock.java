package com.interview.notes.code.year.y2023.july23.test6;

public class Stock {
    private final String name;
    private final int shares;
    private final double price;

    public Stock(String name, int shares, double price) {
        this.name = name;
        this.shares = shares;
        this.price = price;
    }

    public double getTotalValue() {
        return shares * price;
    }

    // Add getters and setters if needed
}
