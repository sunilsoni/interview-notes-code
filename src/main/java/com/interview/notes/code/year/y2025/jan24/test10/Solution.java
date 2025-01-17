package com.interview.notes.code.year.y2025.jan24.test10;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        try {
            PositionSvc positionSvc = new PositionSvc();
            QuoteSvc quoteSvc = new QuoteSvc();
            calculateAndDisplayAccountValues(positionSvc.getCustomerPositions(), quoteSvc.getAllPreviousClose());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void calculateAndDisplayAccountValues(List<Position> positions, List<Quote> quotes) {
        // Create price lookup map
        Map<String, BigDecimal> priceMap = new HashMap<>();
        for (Quote quote : quotes) {
            priceMap.put(quote.getSymbol(), quote.getPrice());
        }

        // Calculate account values
        Map<String, BigDecimal> accountValues = new HashMap<>();

        for (Position pos : positions) {
            BigDecimal price = priceMap.get(pos.getSymbol());
            if (price == null) {
                System.err.println("Warning: No price found for symbol: " + pos.getSymbol());
                continue;
            }

            BigDecimal positionValue = price.multiply(BigDecimal.valueOf(pos.getNumberOfShares()));
            accountValues.merge(pos.getAccountId(), positionValue, BigDecimal::add);
        }

        // Sort and display results
        System.out.println("accountId: totalValue");
        accountValues.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s: %.2f%n",
                        entry.getKey(),
                        entry.getValue().setScale(2, RoundingMode.HALF_UP)));
    }
}
