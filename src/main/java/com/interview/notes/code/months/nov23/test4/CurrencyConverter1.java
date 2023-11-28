package com.interview.notes.code.months.nov23.test4;

import java.util.HashMap;
import java.util.Map;

/**
 * Stripe operates in many countries and accepts and pays out in lots of different currencies.
 * There’s often a need for Stripe to convert between currencies, which is called "foreign exchange" (FX).
 * Depending on what bank we have a partnership with will determine what currency pairs can be swapped, and at what rate.
 * Your task is to write a program that evaluates foreign exchange opportunities. An example input string looks like this:
 * AUD:USD:0.75,USD:CAD:1.26,USD:JPY:109.28,GBP:JPY:150.15
 * Each comma-separated element represents a currency pair and a conversion rate, where a single unit of the first currency may be exchanged
 * for the given value of the second. For instance, using the example input, one AUD can be swapped for 0.75 USD. The conversion is symmetric,
 * so 1 USD may be swapped for 1/0.75 = 1.333 AUD.
 * Your program will read and parse that input string.
 * Write a function that can look up direct conversions from the input list. For instance,
 * f("AUD", "USD") should return 0.75
 * f("USD", "AUD") should return 1.333
 */
public class CurrencyConverter1 {
    private Map<String, Double> conversionRatesMap = new HashMap<>();

    public CurrencyConverter1(String input) {
        parseAndStoreRates(input);
    }

    public static void main(String[] args) {
        CurrencyConverter1 converter = new CurrencyConverter1("AUD:USD:0.75,USD:CAD:1.26,JPY:USD:0.129,EUR:GBP:1.156");
        System.out.println("AUD to USD: " + converter.getConversionRate("AUD", "USD")); // should print 0.75
        System.out.println("USD to AUD: " + converter.getConversionRate("USD", "AUD")); // should print approximately 1.3333
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
                conversionRatesMap.put(inverseKey, 1 / rate);
            }
        }
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

/**
 * OUTPUT:
 * <p>
 * AUD to USD: 0.75
 * USD to AUD: 1.3333333333333333
 */
