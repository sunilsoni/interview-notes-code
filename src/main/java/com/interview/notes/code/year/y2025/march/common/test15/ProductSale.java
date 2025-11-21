package com.interview.notes.code.year.y2025.march.common.test15;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductSale {
    private final int id;
    private final LocalDateTime dateTime;
    private final int quantity;

    public ProductSale(int id, String dateTimeStr, int quantity) {
        this.id = id;
        this.dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getQuantity() {
        return quantity;
    }
}
