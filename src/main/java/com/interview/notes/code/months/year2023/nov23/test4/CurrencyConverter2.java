package com.interview.notes.code.months.year2023.nov23.test4;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter2 {
    private Map<String, Double> conversionRatesMap = new HashMap<>();

    public CurrencyConverter2(String input) {
        parseAndStoreRates(input);
    }

    public static void main(String[] args) {
        CurrencyConverter2 converter = new CurrencyConverter2("AUD:USD:0.75,USD:CAD:1.26,JPY:USD:0.129,EUR:GBP:1.156");
        System.out.println("AUD to USD: " + converter.getConversionRate("AUD", "USD")); // should print 0.75
        System.out.println("USD to AUD: " + converter.getConversionRate("USD", "AUD")); // should print 1.333
    }

    private void parseAndStoreRates(String input) {
        String[] entries = input.split(",");
        for (String entry : entries) {
            String[] parts = entry.split(":");
            if (parts.length == 3) {
                String fromCurrency = parts[0].trim();
                String toCurrency = parts[1].trim();
                double rate = Double.parseDouble(parts[2].trim());

                String directKey = fromCurrency + ":" + toCurrency;
                String inverseKey = toCurrency + ":" + fromCurrency;
                conversionRatesMap.put(directKey, rate);
                conversionRatesMap.put(inverseKey, roundToThreeDecimals(1 / rate));
            }
        }
    }

    private double roundToThreeDecimals(double value) {
        return Math.round(value * 1000) / 1000.0;
    }

    public double getConversionRate(String fromCurrency, String toCurrency) {
        String key = fromCurrency + ":" + toCurrency;
        if (conversionRatesMap.containsKey(key)) {
            return conversionRatesMap.get(key);
        } else {
            throw new IllegalArgumentException("Conversion rate for " + key + " not available.");
        }
    }
}
