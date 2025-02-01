package com.interview.notes.code.year.y2025.jan25.test8;

import java.util.HashMap;
import java.util.Map;

public class AccountValueCalculator {

    // Method to calculate total account values
    public static Map<String, Double> calculateAccountValues(Map<String, Map<String, double[]>> accounts) {
        Map<String, Double> result = new HashMap<>();

        for (Map.Entry<String, Map<String, double[]>> account : accounts.entrySet()) {
            String accountId = account.getKey();
            Map<String, double[]> holdings = account.getValue();

            double totalValue = 0.0;
            for (double[] data : holdings.values()) {
                double shares = data[0];
                double price = data[1];
                if (shares > 0 && price > 0) {
                    totalValue += shares * price;
                }
            }
            result.put(accountId, totalValue);
        }
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        // Test data setup
        Map<String, Map<String, double[]>> accounts = new HashMap<>();

        Map<String, double[]> account1 = new HashMap<>();
        account1.put("WMT", new double[]{10, 141.15});
        account1.put("MCD", new double[]{5, 211.79});
        accounts.put("286ea600", account1);

        Map<String, double[]> account2 = new HashMap<>();
        account2.put("AAPL", new double[]{20, 150.25});
        account2.put("GOOG", new double[]{10, 2750.50});
        accounts.put("b949203a", account2);

        Map<String, Double> results = calculateAccountValues(accounts);

        // Display results
        for (Map.Entry<String, Double> entry : results.entrySet()) {
            System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue());
        }

        // Additional test for large data
        Map<String, double[]> largeData = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            largeData.put("SYM" + i, new double[]{100, 50});
        }
        accounts.put("largeAccount", largeData);

        long startTime = System.currentTimeMillis();
        results = calculateAccountValues(accounts);
        long endTime = System.currentTimeMillis();

        System.out.printf("largeAccount: %.2f (Processed in %d ms)%n", results.get("largeAccount"), (endTime - startTime));
    }
}
