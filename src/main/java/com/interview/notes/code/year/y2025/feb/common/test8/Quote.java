package com.interview.notes.code.year.y2025.feb.common.test8;

import java.math.BigDecimal;

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

    public void setSymbol(String sym) {
        this.symbol = sym;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Quote [symbol=%s, price=%s]", symbol, price);
    }
}
