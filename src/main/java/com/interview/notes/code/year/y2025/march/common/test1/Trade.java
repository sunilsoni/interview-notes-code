package com.interview.notes.code.year.y2025.march.common.test1;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Trade(
    String tradeId,
    String stockName,
    TradeDirection direction,
    LocalDate tradeDate,
    int quantity,
    BigDecimal price
) {
    public enum TradeDirection {
        BUY,
        SELL
    }

    public BigDecimal getValue() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}

class Main {
    public static void main(String[] args) {
        BigDecimal totalValue = BigDecimal.ZERO;

        for (int i = 1; i <= 10; i++) {
            var trade = new Trade(
                "T" + i,
                "AAPL",
                Trade.TradeDirection.BUY,
                LocalDate.of(2025, 4, 4),
                10,
                new BigDecimal("190.50")  // Use String constructor for exact decimal representation
            );

            BigDecimal tradeValue = trade.getValue();
            totalValue = totalValue.add(tradeValue);

            System.out.println("Trade " + i + ": " + trade + ", Value: " + tradeValue);
        }

        System.out.println("\nTotal value of all trades: " + totalValue);
    }
}
