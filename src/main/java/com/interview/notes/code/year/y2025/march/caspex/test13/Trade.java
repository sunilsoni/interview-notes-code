package com.interview.notes.code.year.y2025.march.caspex.test13;

import java.time.LocalDate;

public record Trade(
    String tradeId,
    String stockName,
    TradeDirection direction,
    LocalDate tradeDate,
    int quantity,
    double price
) {
    public enum TradeDirection {
        BUY,
        SELL
    }
}

class Main {
    public static void main(String[] args) {
        var trade = new Trade(
            "T123",
            "AAPL",
            Trade.TradeDirection.BUY,
            LocalDate.of(2025, 4, 4),
            10,
            190.50
        );

        System.out.println(trade);
    }
}
