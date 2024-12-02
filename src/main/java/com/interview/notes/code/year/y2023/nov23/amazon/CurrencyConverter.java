package com.interview.notes.code.year.y2023.nov23.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class CurrencyConverter {
    private List<CurrencyNode> currencyNodes;

    public CurrencyConverter() {
        currencyNodes = new ArrayList<>();
    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        converter.addCurrency("USD");
        converter.addCurrency("EUR");
        converter.addCurrency("GBP");

        converter.addConversionRate("USD", "EUR", 0.9);
        converter.addConversionRate("EUR", "GBP", 1.2);

        try {
            double result = converter.convertCurrency("USD", "EUR", 100);
            System.out.println("100 USD in Euro: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCurrency(String currencyCode) {
        currencyNodes.add(new CurrencyNode(currencyCode));
    }

    public void addConversionRate(String fromCurrency, String toCurrency, double rate) {
        for (CurrencyNode node : currencyNodes) {
            if (node.currencyCode.equals(fromCurrency)) {
                node.addConversionRate(toCurrency, rate);
                return;
            }
        }
        throw new IllegalArgumentException("Currency not found: " + fromCurrency);
    }

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        // DFS to find the conversion rate
        Stack<ConversionRate> stack = new Stack<>();
        for (CurrencyNode node : currencyNodes) {
            if (node.currencyCode.equals(fromCurrency)) {
                for (ConversionRate rate : node.rates) {
                    stack.push(rate);
                }
                break;
            }
        }

        while (!stack.isEmpty()) {
            ConversionRate currentRate = stack.pop();
            if (currentRate.targetCurrency.equals(toCurrency)) {
                return amount * currentRate.rate;
            }

            // Push rates of the target currency of the current rate
            for (CurrencyNode node : currencyNodes) {
                if (node.currencyCode.equals(currentRate.targetCurrency)) {
                    for (ConversionRate rate : node.rates) {
                        stack.push(rate);
                    }
                }
            }
        }

        throw new IllegalArgumentException("Conversion path not found from " + fromCurrency + " to " + toCurrency);
    }
}