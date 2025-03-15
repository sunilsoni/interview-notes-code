package com.interview.notes.code.year.y2025.march.common.test15;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ProductSale> sales = Arrays.asList(
                new ProductSale(1, "13-03-2025 14:00:00", 12),
                new ProductSale(2, "12-03-2025 15:15:00", 10),
                new ProductSale(3, "11-03-2025 15:49:00", 28),
                new ProductSale(4, "12-02-2025 15:01:00", 13)
        );

        int total = ProductSalesAnalyzer.totalProductsSoldBetween3To4PM(sales);
        System.out.println("Total products sold between 3PM and 4PM: " + total);
    }
}
