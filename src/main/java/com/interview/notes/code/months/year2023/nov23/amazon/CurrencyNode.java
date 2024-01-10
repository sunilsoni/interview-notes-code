package com.interview.notes.code.months.year2023.nov23.amazon;

import java.util.ArrayList;
import java.util.List;

class CurrencyNode {
    String currencyCode;
    List<ConversionRate> rates;

    public CurrencyNode(String currencyCode) {
        this.currencyCode = currencyCode;
        this.rates = new ArrayList<>();
    }

    public void addConversionRate(String toCurrency, double rate) {
        rates.add(new ConversionRate(toCurrency, rate));
    }
}