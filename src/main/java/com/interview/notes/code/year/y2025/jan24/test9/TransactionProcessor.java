package com.interview.notes.code.year.y2025.jan24.test9;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Transaction {
    private int accountNo;
    private String date;
    private double amountTrans;

    public Transaction(int accountNo, String date, double amountTrans) {
        this.accountNo = accountNo;
        this.date = date;
        this.amountTrans = amountTrans;
    }

    // Required for proper comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return accountNo == that.accountNo &&
                Double.compare(that.amountTrans, amountTrans) == 0 &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo, date, amountTrans);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNo=" + accountNo +
                ", date='" + date + '\'' +
                ", amount=" + amountTrans +
                '}';
    }
}

public class TransactionProcessor {
    public static void main(String[] args) {
        // Test Case 1: Basic distinct check
        testBasicDistinct();

        // Test Case 2: Empty list
        testEmptyList();

        // Test Case 3: Large data set
        testLargeDataSet();

        // Test Case 4: All unique transactions
        testAllUnique();

        // Test Case 5: All duplicate transactions
        testAllDuplicates();
    }

    private static void testBasicDistinct() {
        System.out.println("Test 1: Basic Distinct Check");
        List<Transaction> transactions = List.of(
                new Transaction(1, "2024-05-05", 84.4),
                new Transaction(1, "2024-05-05", 84.4),
                new Transaction(2, "2024-04-04", 85.0)
        );

        List<Transaction> distinct = findDistinct(transactions);
        boolean passed = distinct.size() == 2;
        System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
        System.out.println("Distinct count: " + distinct.size());
        distinct.forEach(System.out::println);
        System.out.println();
    }

    private static void testEmptyList() {
        System.out.println("Test 2: Empty List Check");
        List<Transaction> transactions = new ArrayList<>();
        List<Transaction> distinct = findDistinct(transactions);
        boolean passed = distinct.isEmpty();
        System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testLargeDataSet() {
        System.out.println("Test 3: Large Data Set");
        List<Transaction> transactions = new ArrayList<>();
        // Generate 100,000 transactions (with some duplicates)
        for (int i = 0; i < 100000; i++) {
            transactions.add(new Transaction(i % 1000, "2024-05-05", 84.4));
        }

        long startTime = System.currentTimeMillis();
        List<Transaction> distinct = findDistinct(transactions);
        long endTime = System.currentTimeMillis();

        boolean passed = distinct.size() == 1000; // Should be 1000 unique combinations
        System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
        System.out.println();
    }

    private static void testAllUnique() {
        System.out.println("Test 4: All Unique Transactions");
        List<Transaction> transactions = List.of(
                new Transaction(1, "2024-05-05", 84.4),
                new Transaction(2, "2024-05-06", 85.4),
                new Transaction(3, "2024-05-07", 86.4)
        );

        List<Transaction> distinct = findDistinct(transactions);
        boolean passed = distinct.size() == transactions.size();
        System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testAllDuplicates() {
        System.out.println("Test 5: All Duplicates");
        List<Transaction> transactions = List.of(
                new Transaction(1, "2024-05-05", 84.4),
                new Transaction(1, "2024-05-05", 84.4),
                new Transaction(1, "2024-05-05", 84.4)
        );

        List<Transaction> distinct = findDistinct(transactions);
        boolean passed = distinct.size() == 1;
        System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static List<Transaction> findDistinct(List<Transaction> transactions) {
        return transactions.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
