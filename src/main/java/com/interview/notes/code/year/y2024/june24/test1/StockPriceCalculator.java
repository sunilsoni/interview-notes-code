package com.interview.notes.code.year.y2024.june24.test1;

import java.util.Map;
import java.util.TreeMap;

public class StockPriceCalculator {
    private final TreeMap<Long, Double> stockPrices;

    public StockPriceCalculator() {
        stockPrices = new TreeMap<>();
    }

    public static void main(String[] args) {
        StockPriceCalculator spc = new StockPriceCalculator();

        // Adding stock prices
        spc.addStockPrice(1717777305530L, 101.78);
        spc.addStockPrice(1717775095530L, 90.40);
        spc.addStockPrice(1717773025530L, 100.50);
        spc.addStockPrice(1717774055530L, 101.58);
        spc.addStockPrice(1717776205530L, 99.23);

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
        Map<Long, Double> rangePrices = spc.getPricesInRange(startTime, endTime);
        System.out.println("Prices from " + startTime + " to " + endTime + ":");
        for (Map.Entry<Long, Double> entry : rangePrices.entrySet()) {
            System.out.println("Time: " + entry.getKey() + ", Price: " + entry.getValue());
        }
    }

    // 1. Add a new stock price entry
    public void addStockPrice(long timestamp, double price) {
        stockPrices.put(timestamp, price);
    }

    // 2. Retrieve the price for a specific timestamp
    public Double getPriceAt(long timestamp) {
        return stockPrices.get(timestamp);
    }

    // 3. Retrieve the latest stock price
    public Double getLatestPrice() {
        return stockPrices.isEmpty() ? null : stockPrices.lastEntry().getValue();
    }

    // 4. Retrieve the earliest stock price
    public Double getEarliestPrice() {
        return stockPrices.isEmpty() ? null : stockPrices.firstEntry().getValue();
    }

    // 5. Retrieve all stock prices within a specific time range
    public Map<Long, Double> getPricesInRange(long startTime, long endTime) {
        return stockPrices.subMap(startTime, true, endTime, true);
    }
}
