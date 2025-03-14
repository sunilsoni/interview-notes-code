package com.interview.notes.code.year.y2025.march.common.test15;

import java.time.LocalTime;
import java.util.List;

public class ProductSalesAnalyzer {

    public static int totalProductsSoldBetween3To4PM(List<ProductSale> sales) {
        LocalTime start = LocalTime.of(15, 0); // 3:00 PM
        LocalTime end = LocalTime.of(16, 0);   // 4:00 PM

        return sales.stream()
                .filter(sale -> {
                    LocalTime saleTime = sale.getDateTime().toLocalTime();
                    return !saleTime.isBefore(start) && saleTime.isBefore(end);
                })
                .mapToInt(ProductSale::getQuantity)
                .sum();
    }
}
