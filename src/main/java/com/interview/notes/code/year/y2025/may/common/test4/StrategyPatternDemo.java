package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Enumeration of supported payment methods
enum PaymentMethod {
    CREDIT_CARD,    // Pay via credit card
    PAYPAL,         // Pay via PayPal
    BANK_TRANSFER   // Pay via bank transfer
}

// Functional interface defining the contract for any payment operation
@FunctionalInterface
interface PaymentStrategy {
    // Processes a payment of given amount with a description, returns a result message
    String pay(double amount, String description);
}

// Core processor that chooses and executes the right PaymentStrategy
class PaymentProcessor {
    // Registry mapping each PaymentMethod to its lambda implementation
    private final Map<PaymentMethod, PaymentStrategy> strategies = new HashMap<>();

    // Populate registry with lambdas for each supported method
    public PaymentProcessor() {
        strategies.put(
                PaymentMethod.CREDIT_CARD,
                (amount, desc) ->
                        String.format("Processed $%.2f via Credit Card. Description: %s", amount, desc)
        );
        strategies.put(
                PaymentMethod.PAYPAL,
                (amount, desc) ->
                        String.format("Processed $%.2f via PayPal. Description: %s", amount, desc)
        );
        strategies.put(
                PaymentMethod.BANK_TRANSFER,
                (amount, desc) ->
                        String.format("Processed $%.2f via Bank Transfer. Description: %s", amount, desc)
        );
    }

    // Looks up the strategy by method and invokes it; errors out if unknown
    public String processPayment(PaymentMethod method, double amount, String description) {
        PaymentStrategy strategy = strategies.get(method);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown payment method: " + method);
        }
        return strategy.pay(amount, description);
    }
}

// Simple data holder for a payment request
class PaymentRequest {
    private final PaymentMethod method;   // which strategy to use
    private final double amount;          // how much to pay
    private final String description;     // details for logging

    public PaymentRequest(PaymentMethod method, double amount, String description) {
        this.method = method;
        this.amount = amount;
        this.description = description;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

// Harness to run test cases and large-data throughput test
public class StrategyPatternDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // --- Fixed test cases for correctness ---
        class TestCase {
            PaymentMethod method;
            double amt;
            String desc, expected;

            TestCase(PaymentMethod m, double a, String d, String e) {
                method = m;
                amt = a;
                desc = d;
                expected = e;
            }
        }
        List<TestCase> tests = Arrays.asList(
                new TestCase(
                        PaymentMethod.CREDIT_CARD, 100.0, "Order#1",
                        "Processed $100.00 via Credit Card. Description: Order#1"
                ),
                new TestCase(
                        PaymentMethod.PAYPAL, 250.5, "Order#2",
                        "Processed $250.50 via PayPal. Description: Order#2"
                ),
                new TestCase(
                        PaymentMethod.BANK_TRANSFER, 5000, "Invoice#123",
                        "Processed $5000.00 via Bank Transfer. Description: Invoice#123"
                )
        );

        // Execute and report PASS/FAIL
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            String result = processor.processPayment(tc.method, tc.amt, tc.desc);
            String status = result.equals(tc.expected) ? "PASS" : "FAIL";
            System.out.printf("Test %d: %s → %s%n", i + 1, status, result);
        }

        // --- Large-data throughput test ---
        // Generate 100_000 requests cycling through methods
        List<PaymentRequest> bulk = IntStream.range(0, 100_000)
                .mapToObj(i -> new PaymentRequest(
                        PaymentMethod.values()[i % PaymentMethod.values().length],
                        i * 1.0,
                        "Bulk#" + i
                )).collect(Collectors.toList());

        long start = System.currentTimeMillis();
        // Process each via stream.peek and then count to force evaluation
        long count = bulk.stream()
                .peek(req ->
                        processor.processPayment(req.getMethod(), req.getAmount(), req.getDescription())
                )
                .count();
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("Processed %,d requests in %d ms%n", count, elapsed);
    }
}