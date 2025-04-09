package com.interview.notes.code.year.y2025.march.caspex.test12;

import java.time.LocalDate;

public record Trade(
        String tradeId,
        String stockName,
        TradeDirection direction,
        LocalDate tradeDate,
        int quantity,
        double price
) {
    public double getValue() {
        return quantity * price;
    }

    public enum TradeDirection {
        BUY,
        SELL
    }
}

class Main {
    public static void main(String[] args) {
        double totalValue = 0.0;

        for (int i = 1; i <= 10; i++) {
            var trade = new Trade(
                    "T" + i,
                    "AAPL",
                    Trade.TradeDirection.BUY,
                    LocalDate.of(2025, 4, 4),
                    10,
                    190.50
            );

            double tradeValue = trade.getValue();
            totalValue += tradeValue;

            System.out.printf("Trade %d: %s, Value: $%.2f%n", i, trade, tradeValue);
        }

        System.out.printf("%nTotal value of all trades: $%.2f%n", totalValue);
    }
}
