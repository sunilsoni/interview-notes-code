package com.interview.notes.code.year.y2025.december.common.test3;// Traditional approach vs Stream API approach
// Scenario: Filter transactions > $1000, group by type, calculate totals

import java.math.BigDecimal;
import java.util.*;

record Transaction(String type, BigDecimal amount, String customerId) {
    // Constructor and getters
}

class TransactionProcessor {

    // Main method to test both approaches
    public static void main(String[] args) {
        TransactionProcessor processor = new TransactionProcessor();

        // Sample transactions
        List<Transaction> transactions = Arrays.asList(
                new Transaction("DEBIT", new BigDecimal("500"), "CUST-001"),
                new Transaction("CREDIT", new BigDecimal("1500"), "CUST-002"),
                new Transaction("DEBIT", new BigDecimal("2000"), "CUST-003"),
                new Transaction("TRANSFER", new BigDecimal("800"), "CUST-004"),
                new Transaction("CREDIT", new BigDecimal("3500"), "CUST-005"),
                new Transaction("DEBIT", new BigDecimal("1200"), "CUST-006")
        );

        // Traditional approach
        long startTraditional = System.currentTimeMillis();
        Map<String, BigDecimal> resultTraditional =
                processor.processTransactionsTraditional(transactions);
        long timeTraditional = System.currentTimeMillis() - startTraditional;

        // Stream API approach
        long startStream = System.currentTimeMillis();
        Map<String, BigDecimal> resultStream =
                processor.processTransactionsWithStreams(transactions);
        long timeStream = System.currentTimeMillis() - startStream;

        System.out.println("Traditional Approach Result: " + resultTraditional);
        System.out.println("Traditional Time: " + timeTraditional + "ms\n");

        System.out.println("Stream API Result: " + resultStream);
        System.out.println("Stream Time: " + timeStream + "ms");
    }

    // TODO: Implement traditional loop approach
    public Map<String, BigDecimal> processTransactionsTraditional(
            List<Transaction> transactions) {

        Map<String, BigDecimal> result = new HashMap<>();

        // First loop: Filter transactions > $1000
        List<Transaction> highValueTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.amount() != null &&
                    transaction.amount().compareTo(BigDecimal.valueOf(1000)) > 0) {
                highValueTransactions.add(transaction);
            }
        }

        // Second loop: Group by type
        Map<String, List<Transaction>> groupedByType = new HashMap<>();
        for (Transaction transaction : highValueTransactions) {
            String type = transaction.type();
            if (!groupedByType.containsKey(type)) {
                groupedByType.put(type, new ArrayList<>());
            }
            groupedByType.get(type).add(transaction);
        }

        // Third loop: Calculate totals per type
        for (Map.Entry<String, List<Transaction>> entry : groupedByType.entrySet()) {
            BigDecimal total = BigDecimal.ZERO;
            for (Transaction transaction : entry.getValue()) {
                total = total.add(transaction.amount());
            }
            result.put(entry.getKey(), total);
        }

        return result;
    }

    // TODO: Implement Stream API approach
    public Map<String, BigDecimal> processTransactionsWithStreams(
            List<Transaction> transactions) {

//        return transactions.stream()
//                .filter(transaction -> transaction.getAmount() != null)
//                .filter(transaction -> transaction.getAmount()
//                        .compareTo(BigDecimal.valueOf(1000)) > 0)
//                .collect(Collectors.groupingBy(
//                        Transaction::getType,
//                        Collectors.summingDouble(Transaction::getAmount)
//                ));

        return null;
    }
}