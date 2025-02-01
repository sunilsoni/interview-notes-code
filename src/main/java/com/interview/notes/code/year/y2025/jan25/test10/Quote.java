package com.interview.notes.code.year.y2025.jan25.test10;

import java.math.BigDecimal;

// Quote class
class Quote {
    private String symbol;
    private BigDecimal price;

    public Quote(String quoteStr) {
        String[] tokens = quoteStr.split(",");
        this.symbol = tokens[0];
        this.price = new BigDecimal(tokens[1]);
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Quote [symbol=%s, price=%s]", symbol, price);
    }
}