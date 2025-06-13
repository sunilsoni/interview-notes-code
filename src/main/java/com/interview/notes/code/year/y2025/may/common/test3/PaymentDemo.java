package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.Map;
import java.util.HashMap;

public class PaymentDemo {
    @FunctionalInterface
    interface PaymentStrategy {
        String pay(double amount, String description);
    }

    enum PaymentMethod {
        CREDIT_CARD,
        PAYPAL,
        BANK_TRANSFER
    }

    static class PaymentService {
        private final Map<PaymentMethod, PaymentStrategy> registry = new HashMap<>();

        public PaymentService() {
            registry.put(PaymentMethod.CREDIT_CARD,
                (amount, desc) -> String.format("Processed $%.2f via Credit Card: %s", amount, desc));
            registry.put(PaymentMethod.PAYPAL,
                (amount, desc) -> String.format("Processed $%.2f via PayPal: %s", amount, desc));
            // Note: BANK_TRANSFER not registered to demonstrate error handling
        }

        public String processPayment(PaymentMethod method, double amount, String description) {
            PaymentStrategy strategy = registry.get(method);
            if (strategy == null) {
                throw new IllegalArgumentException("No payment strategy found for method: " + method);
            }
            return strategy.pay(amount, description);
        }
    }

    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        // Existing method
        String result1 = service.processPayment(PaymentMethod.CREDIT_CARD, 100.00, "Order #1234");
        System.out.println(result1);

        // Non-existing method to validate error handling
        try {
            String result2 = service.processPayment(PaymentMethod.BANK_TRANSFER, 200.00, "Order #5678");
            System.out.println(result2);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}