package com.interview.notes.code.months.june24.test2;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class StockPriceCalculator {
    private TreeMap<Long, BigDecimal> stockPrices;

    public StockPriceCalculator() {
        stockPrices = new TreeMap<>();
    }

    public static void main(String[] args) {
        StockPriceCalculator spc = new StockPriceCalculator();

        // Adding stock prices
        spc.addStockPrice(1717777305530L, new BigDecimal("101.78"));
        spc.addStockPrice(1717775095530L, new BigDecimal("90.40"));
        spc.addStockPrice(1717773025530L, new BigDecimal("100.50"));
        spc.addStockPrice(1717774055530L, new BigDecimal("101.58"));
        spc.addStockPrice(1717776205530L, new BigDecimal("99.23"));

        // Retrieve and print the earliest price
        System.out.println("Earliest Price: " + spc.getEarliestPrice());

        // Retrieve and print the latest price
        System.out.println("Latest Price: " + spc.getLatestPrice());

        // Retrieve and print the price at a specific timestamp
        System.out.println("Price at 1717777305530L: " + spc.getPriceAt(1717777305530L));
        System.out.println("Price at 1717774055530L: " + spc.getPriceAt(1717774055530L));

        // Retrieve and print the prices within a specific time range
        long startTime = 1717773025530L;
        long endTime = 1717776205530L;
        Map<Long, BigDecimal> rangePrices = spc.getPricesInRange(startTime, endTime);
        System.out.println("Prices from " + startTime + " to " + endTime + ":");
        for (Map.Entry<Long, BigDecimal> entry : rangePrices.entrySet()) {
            System.out.println("Time: " + entry.getKey() + ", Price: " + entry.getValue());
        }
    }

    // 1. Add a new stock price entry
    public void addStockPrice(long timestamp, BigDecimal price) {
        stockPrices.put(timestamp, price);
    }

    // 2. Retrieve the price for a specific timestamp
    public BigDecimal getPriceAt(long timestamp) {
        return stockPrices.get(timestamp);
    }

    // 3. Retrieve the latest stock price
    public BigDecimal getLatestPrice() {
        return stockPrices.isEmpty() ? null : stockPrices.lastEntry().getValue();
    }

    // 4. Retrieve the earliest stock price
    public BigDecimal getEarliestPrice() {
        return stockPrices.isEmpty() ? null : stockPrices.firstEntry().getValue();
    }

    // 5. Retrieve all stock prices within a specific time range
    public Map<Long, BigDecimal> getPricesInRange(long startTime, long endTime) {
        return stockPrices.subMap(startTime, true, endTime, true);
    }
}
