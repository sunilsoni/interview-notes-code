package com.interview.notes.code.months.year2023.nov23.test6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionExample {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("Food", 50),
                new Transaction("Travel", 300),
                new Transaction("Food", 30),
                new Transaction("Charity", 10),
                new Transaction("Travel", 200),
                new Transaction("Charity", 5)
        );

        Map<String, List<Transaction>> groupedByCategory = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory));

        groupedByCategory.forEach((category, categoryTransactions) -> {
            long numberOfTransactions = categoryTransactions.size();
            int totalAmount = categoryTransactions.stream()
                    .mapToInt(Transaction::getAmount)
                    .sum();
            System.out.println(category + " - " + numberOfTransactions + " - " + totalAmount);
        });
    }
}

class Transaction {
    private String category;
    private int amount;

    public Transaction(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }
}
