package com.interview.notes.code.year.y2025.march.common.test15;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

class Sale {
    private int id;
    private LocalDateTime dateTime;
    private int quantitySold;

    public Sale(int id, String dateTimeStr, int quantitySold) {
        this.id = id;
        this.dateTime = LocalDateTime.parse(dateTimeStr, 
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.quantitySold = quantitySold;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getQuantitySold() {
        return quantitySold;
    }
}

public class SalesAnalyzer {
    public static int getTotalProductsSoldBetween3And4PM(List<Sale> sales) {
        return sales.stream()
            .filter(sale -> {
                int hour = sale.getDateTime().getHour();
                return hour >= 15 && hour < 16;
            })
            .mapToInt(Sale::getQuantitySold)
            .sum();
    }

    public static void main(String[] args) {
        List<Sale> sales = List.of(
            new Sale(1, "13-03-2025 14:00:00", 12),
            new Sale(2, "12-03-2025 15:15:00", 10),
            new Sale(3, "11-03-2025 15:49:00", 28),
            new Sale(4, "12-02-2025 15:01:00", 13)
        );

        int totalSold = getTotalProductsSoldBetween3And4PM(sales);
        System.out.println("Total products sold between 3 PM and 4 PM: " + totalSold);
    }
}
