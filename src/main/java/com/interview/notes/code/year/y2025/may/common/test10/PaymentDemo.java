package com.interview.notes.code.year.y2025.may.common.test10;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PaymentDemo {

    // --- shared types ---
    enum PaymentMethod {
        CREDIT_CARD,
        PAYPAL,
        BANK_TRANSFER
    }

    @FunctionalInterface
    interface PaymentStrategy {
        String process(double amount, String description);
    }

    // --- 1) Optional-based service ---
    static class PaymentServiceOptional {
        private final Map<PaymentMethod, PaymentStrategy> registry = new HashMap<>();

        PaymentServiceOptional() {
            registry.put(PaymentMethod.CREDIT_CARD,
                (amt, desc) -> String.format("Processed $%.2f via Credit Card: %s", amt, desc));
            registry.put(PaymentMethod.PAYPAL,
                (amt, desc) -> String.format("Processed $%.2f via PayPal: %s", amt, desc));
            // BANK_TRANSFER intentionally not registered
        }

        Optional<String> processPayment(PaymentMethod method, double amount, String description) {
            return Optional.ofNullable(registry.get(method))
                           .map(strat -> strat.process(amount, description));
        }
    }

    // --- 2) Exception-based service ---
    static class PaymentServiceException {
        private final Map<PaymentMethod, PaymentStrategy> registry = new HashMap<>();

        PaymentServiceException() {
            registry.put(PaymentMethod.CREDIT_CARD,
                (amt, desc) -> String.format("Processed $%.2f via Credit Card: %s", amt, desc));
            registry.put(PaymentMethod.PAYPAL,
                (amt, desc) -> String.format("Processed $%.2f via PayPal: %s", amt, desc));
            // BANK_TRANSFER intentionally not registered
        }

        String processPayment(PaymentMethod method, double amount, String description) {
            PaymentStrategy strat = registry.get(method);
            if (strat == null) {
                throw new IllegalArgumentException("Unsupported payment method: " + method);
            }
            return strat.process(amount, description);
        }
    }

    // --- test harness ---
    public static void main(String[] args) {
        PaymentServiceOptional optSvc = new PaymentServiceOptional();
        System.out.println("=== Optional-based Service Tests ===");
        testOptional(optSvc, PaymentMethod.CREDIT_CARD, 100.00, "Order #1234", true);
        testOptional(optSvc, PaymentMethod.BANK_TRANSFER, 250.00, "Order #5678", false);

        PaymentServiceException excSvc = new PaymentServiceException();
        System.out.println("\n=== Exception-based Service Tests ===");
        testException(excSvc, PaymentMethod.CREDIT_CARD, 100.00, "Order #1234", true);
        testException(excSvc, PaymentMethod.BANK_TRANSFER, 250.00, "Order #5678", false);
    }

    private static void testOptional(PaymentServiceOptional svc,
                                     PaymentMethod method,
                                     double amt,
                                     String desc,
                                     boolean shouldSucceed) {
        Optional<String> result = svc.processPayment(method, amt, desc);
        if (result.isPresent() == shouldSucceed) {
            result.ifPresent(System.out::println);
            System.out.printf("Test %s: PASS%n", method);
        } else {
            System.out.printf("Test %s: FAIL%n", method);
        }
    }

    private static void testException(PaymentServiceException svc,
                                      PaymentMethod method,
                                      double amt,
                                      String desc,
                                      boolean shouldSucceed) {
        try {
            String res = svc.processPayment(method, amt, desc);
            System.out.println(res);
            if (shouldSucceed) {
                System.out.printf("Test %s: PASS%n", method);
            } else {
                System.out.printf("Test %s: FAIL%n", method);
            }
        } catch (IllegalArgumentException e) {
            if (!shouldSucceed) {
                System.out.printf("Test %s (exception thrown): PASS%n", method);
            } else {
                System.out.printf("Test %s: FAIL%n", method);
            }
        }
    }
}