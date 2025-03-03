package com.interview.notes.code.year.y2025.feb.common.test8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Minimal example demonstrating how to:
 * 1. Retrieve positions and quotes
 * 2. Compute total account values
 * 3. Sort descending
 * 4. Print results
 * 5. Provide a simple test harness (no JUnit)
 */
public class MinimalReproSolution {

    // ----------- POJO classes ----------- //

    public static void main(String[] args) {
        // Create our "services" with sample data
        PositionSvc positionSvc = new PositionSvc();
        QuoteSvc quoteSvc = new QuoteSvc();

        // 1) Build a Map of symbol -> price
        Map<String, BigDecimal> symbolPriceMap = quoteSvc.getAllPreviousClose()
                .stream()
                .collect(Collectors.toMap(
                        Quote::getSymbol,      // key = symbol
                        Quote::getPrice));     // value = price

        // 2) For each account, sum up (shareCount * sharePrice)
        //    Using groupingBy + reducing in Java 8 Streams
        Map<String, BigDecimal> accountTotals = positionSvc.getCustomerPositions()
                .stream()
                // Filter out positions that have no quote in the map if you like
                .filter(p -> symbolPriceMap.containsKey(p.getSymbol()))
                .collect(Collectors.groupingBy(
                        Position::getAccountId,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                p -> symbolPriceMap.get(p.getSymbol())
                                        .multiply(BigDecimal.valueOf(p.getNumberOfShares())),
                                BigDecimal::add)));

        // 3) Sort accounts by descending total value
        List<Map.Entry<String, BigDecimal>> sortedList = new ArrayList<>(accountTotals.entrySet());
        sortedList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // 4) Print results
        System.out.println("=== Account Totals (Descending) ===");
        sortedList.forEach(entry -> {
            System.out.printf("%s: %.2f\n", entry.getKey(), entry.getValue());
        });

        // Finally, run a simple test to check if result matches an expected scenario
        runTestCase("BasicCheck", accountTotals);
    }

    /**
     * Simple pass/fail test harness (no JUnit).
     * We compare some known or expected values (if we have them).
     * This is just a placeholder to illustrate the idea.
     */
    private static void runTestCase(String testCaseName, Map<String, BigDecimal> actualMap) {
        System.out.println("\nRunning TestCase: " + testCaseName);
        boolean pass = true;

        // Suppose we expected "286ea600" to have around 3000.00 as a total
        BigDecimal expected = new BigDecimal("3000.00");
        BigDecimal actual = actualMap.getOrDefault("286ea600", BigDecimal.ZERO);

        // We do a simple check with a small delta tolerance for floating output
        BigDecimal difference = actual.subtract(expected).abs();
        BigDecimal tolerance = new BigDecimal("10.00"); // arbitrary for example
        if (difference.compareTo(tolerance) > 0) {
            pass = false;
            System.out.println("   FAIL: expected ~ " + expected + " for 286ea600 but got " + actual);
        }

        if (pass) System.out.println("   PASS");
    }

    // ----------- Services ----------- //

    static class Position {
        private String accountId;
        private String symbol;
        private long numShares;

        Position(String positionStr) {
            String[] tokens = positionStr.split(",");
            this.accountId = tokens[0];
            this.symbol = tokens[1];
            this.numShares = Long.valueOf(tokens[2]);
        }

        public String getAccountId() {
            return accountId;
        }

        public String getSymbol() {
            return symbol;
        }

        public long getNumberOfShares() {
            return numShares;
        }

        @Override
        public String toString() {
            return String.format("Position[accountId=%s, symbol=%s, shares=%d]",
                    accountId, symbol, numShares);
        }
    }

    static class Quote {
        private String symbol;
        private BigDecimal price;

        Quote(String quoteStr) {
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
            return String.format("Quote[symbol=%s, price=%s]", symbol, price);
        }
    }

    // ----------- Main logic ----------- //

    static class PositionSvc {
        private List<Position> positions;

        PositionSvc() {
            this.positions = new ArrayList<>();
            // Populate with example data
            // Format: "accountId,symbol,numShares"
            positions.add(new Position("286ea600,PEP,25"));
            positions.add(new Position("3a8f7d92,VFC,35"));
            positions.add(new Position("8f7d928a,NUE,81"));
            positions.add(new Position("286ea600,ECL,23"));
            positions.add(new Position("3a8f7d92,FRT,30"));
            positions.add(new Position("4810b949,DOV,29"));
            // Add many more...
            // (For brevity we show a subset only)
        }

        public List<Position> getCustomerPositions() {
            return positions;
        }
    }

    static class QuoteSvc {
        private List<Quote> previousCloseQuotes;

        QuoteSvc() {
            this.previousCloseQuotes = new ArrayList<>();
            // Format: "symbol,price"
            previousCloseQuotes.add(new Quote("ABBV,91.87"));
            previousCloseQuotes.add(new Quote("BDX,233.54"));
            previousCloseQuotes.add(new Quote("CAH,49.30"));
            // Add many more...
            // (For brevity we show a subset only)
        }

        public List<Quote> getAllPreviousClose() {
            return previousCloseQuotes;
        }
    }

}
