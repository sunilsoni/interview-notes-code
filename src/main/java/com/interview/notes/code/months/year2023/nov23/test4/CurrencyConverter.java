package com.interview.notes.code.months.year2023.nov23.test4;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private final Map<String, Double> conversionRatesMap = new HashMap<>();

    public CurrencyConverter(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }
        parseAndStoreRates(input.toUpperCase()); // Convert input to upper case to handle case sensitivity
    }

    public static void main(String[] args) {
        try {
            CurrencyConverter converter = new CurrencyConverter("AUD:USD:0.75,USD:CAD:1.26,JPY:USD:0.129,EUR:GBP:1.156");
            System.out.println("AUD to USD: " + converter.getConversionRate("AUD", "USD")); // should print 0.75
            System.out.println("USD to AUD: " + converter.getConversionRate("USD", "AUD")); // should print 1.333


            System.out.println("AUD to USD: " + converter.getConversionRateUpdated("AUD", "USD")); // should print 0.75
            System.out.println("USD to AUD: " + converter.getConversionRateUpdated("USD", "AUD")); // should print 1.333
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseAndStoreRates(String input) {
        String[] entries = input.split(",");
        for (String entry : entries) {
            String[] parts = entry.split(":");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid entry format: " + entry);
            }
            String fromCurrency = parts[0].trim();
            String toCurrency = parts[1].trim();
            double rate = Double.parseDouble(parts[2].trim());
            if (rate <= 0) {
                throw new IllegalArgumentException("Conversion rate must be greater than zero.");
            }

            String directKey = fromCurrency + ":" + toCurrency;
            String inverseKey = toCurrency + ":" + fromCurrency;
            conversionRatesMap.put(directKey, rate);
            conversionRatesMap.put(inverseKey, roundToThreeDecimals(1 / rate));
        }
    }

    private double roundToThreeDecimals(double value) {
        return Math.round(value * 1000) / 1000.0;
    }

    public double getConversionRate(String fromCurrency, String toCurrency) {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Currency codes cannot be null.");
        }
        String key = fromCurrency.toUpperCase() + ":" + toCurrency.toUpperCase();
        Double rate = conversionRatesMap.get(key);
        if (rate == null) {
            throw new IllegalArgumentException("Conversion rate for " + key + " not available.");
        }
        return rate;
    }

    public double getConversionRateUpdated(String fromCurrency, String toCurrency) {
        String directKey = fromCurrency + ":" + toCurrency;
        String inverseKey = toCurrency + ":" + fromCurrency;

        // Direct conversion case
        if (conversionRatesMap.containsKey(directKey)) {
            return conversionRatesMap.get(directKey);
        }
        // Inverse conversion case
        else if (conversionRatesMap.containsKey(inverseKey)) {
            return 1 / conversionRatesMap.get(inverseKey);
        }
        // Two-step conversion case using USD as an intermediary
        else {
            String intermediary = "USD";
            String firstStepKey = fromCurrency + ":" + intermediary;
            String secondStepKey = intermediary + ":" + toCurrency;
            String firstStepInverseKey = intermediary + ":" + fromCurrency;
            String secondStepInverseKey = toCurrency + ":" + intermediary;

            if (conversionRatesMap.containsKey(firstStepKey) && conversionRatesMap.containsKey(secondStepKey)) {
                return conversionRatesMap.get(firstStepKey) * conversionRatesMap.get(secondStepKey);
            } else if (conversionRatesMap.containsKey(firstStepInverseKey) && conversionRatesMap.containsKey(secondStepKey)) {
                return (1 / conversionRatesMap.get(firstStepInverseKey)) * conversionRatesMap.get(secondStepKey);
            } else if (conversionRatesMap.containsKey(firstStepKey) && conversionRatesMap.containsKey(secondStepInverseKey)) {
                return conversionRatesMap.get(firstStepKey) * (1 / conversionRatesMap.get(secondStepInverseKey));
            } else if (conversionRatesMap.containsKey(firstStepInverseKey) && conversionRatesMap.containsKey(secondStepInverseKey)) {
                return (1 / conversionRatesMap.get(firstStepInverseKey)) * (1 / conversionRatesMap.get(secondStepInverseKey));
            }
        }

        throw new IllegalArgumentException("Conversion rate for " + fromCurrency + " to " + toCurrency + " is not available");
    }
}
