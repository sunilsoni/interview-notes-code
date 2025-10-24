package com.interview.notes.code.year.y2025.october.Amazon.test3;

import java.util.ArrayList;
import java.util.List;

public class PurchaseValidator {
    // List of validation rules
    private final List<ValidationRule> validationRules = new ArrayList<>();

    // Constructor with default rules
    public PurchaseValidator() {
        // Add default validation rules
        addRule(this::validatePayment);
        addRule(this::validateItems);
        addRule(this::validateAddress);
    }

    // Example usage and testing
    public static void main(String[] args) {
        PurchaseValidator validator = new PurchaseValidator();

        // Add a custom validation rule
        validator.addRule((purchase, result) -> {
            if (purchase.items != null && purchase.items.size() > 10) {
                result.addError("Cannot have more than 10 items");
            }
        });

        // Test cases
        testValidPurchase(validator);
        testInvalidPurchase(validator);
        testCustomRule(validator);
    }

    private static void testValidPurchase(PurchaseValidator validator) {
        System.out.println("\nTesting valid purchase:");
        Purchase purchase = new Purchase();
        purchase.payment = new PaymentInstrument();
        purchase.payment.type = "CREDIT_CARD";
        purchase.items = List.of(new Item());
        purchase.deliveryAddress = "123 Main St";

        ValidationResult result = validator.validate(purchase);
        System.out.println("Is valid: " + result.isValid());
        System.out.println("Errors: " + result.getErrors());
    }

    private static void testInvalidPurchase(PurchaseValidator validator) {
        System.out.println("\nTesting invalid purchase:");
        Purchase purchase = new Purchase();
        ValidationResult result = validator.validate(purchase);
        System.out.println("Is valid: " + result.isValid());
        System.out.println("Errors: " + result.getErrors());
    }

    private static void testCustomRule(PurchaseValidator validator) {
        System.out.println("\nTesting custom rule (>10 items):");
        Purchase purchase = new Purchase();
        purchase.payment = new PaymentInstrument();
        purchase.payment.type = "CREDIT_CARD";
        purchase.deliveryAddress = "123 Main St";
        purchase.items = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            purchase.items.add(new Item());
        }

        ValidationResult result = validator.validate(purchase);
        System.out.println("Is valid: " + result.isValid());
        System.out.println("Errors: " + result.getErrors());
    }

    // Method to add new validation rules
    public void addRule(ValidationRule rule) {
        validationRules.add(rule);
    }

    // Main validation method
    public ValidationResult validate(Purchase purchase) {
        ValidationResult result = new ValidationResult();

        if (purchase == null) {
            result.addError("Purchase cannot be null");
            return result;
        }

        // Execute all validation rules
        validationRules.forEach(rule -> rule.validate(purchase, result));

        return result;
    }

    // Default validation rules implemented as private methods
    private void validatePayment(Purchase purchase, ValidationResult result) {
        if (purchase.payment == null) {
            result.addError("Payment instrument is required");
        } else if (purchase.payment.type == null || !purchase.payment.type.equals("CREDIT_CARD")) {
            result.addError("Invalid payment type");
        }
    }

    private void validateItems(Purchase purchase, ValidationResult result) {
        if (purchase.items == null || purchase.items.isEmpty()) {
            result.addError("Purchase must contain at least one item");
        }
    }

    private void validateAddress(Purchase purchase, ValidationResult result) {
        if (purchase.deliveryAddress == null || purchase.deliveryAddress.trim().isEmpty()) {
            result.addError("Delivery address is required");
        }
    }

    @FunctionalInterface
    public interface ValidationRule {
        void validate(Purchase purchase, ValidationResult result);
    }

    // Model classes remain the same
    public static class Item {
        private String id;
        private double price;
        // getters/setters
    }

    public static class PaymentInstrument {
        private String id;
        private String type;
        // getters/setters
    }

    public static class Purchase {
        private PaymentInstrument payment;
        private List<Item> items;
        private String deliveryAddress;
        // getters/setters
    }

    public static class ValidationResult {
        private final List<String> errors = new ArrayList<>();

        public void addError(String error) {
            errors.add(error);
        }

        public boolean isValid() {
            return errors.isEmpty();
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}
