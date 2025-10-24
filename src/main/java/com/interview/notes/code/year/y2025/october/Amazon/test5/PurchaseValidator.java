package com.interview.notes.code.year.y2025.october.Amazon.test5;

import java.util.*;

/**
 * Model class for Item
 */
class Item {
    private String id;
    private String category;
    private double cost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

/**
 * Model class for PaymentInstrument
 */
class PaymentInstrument {
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

/**
 * Model class for Purchase
 */
class Purchase {
    private PaymentInstrument paymentInstrument;
    private List<Item> items;
    private String deliveryAddressId;

    public PaymentInstrument getPaymentInstrument() {
        return paymentInstrument;
    }

    public void setPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }
}

/**
 * Purchase Validation System
 */
public class PurchaseValidator {

    private final List<ValidationRule> validationRules;

    public PurchaseValidator() {
        validationRules = new ArrayList<>();
        initializeValidationRules();
    }

    public static void main(String[] args) {
        PurchaseValidatorTestSuite.runAllTests();
    }

    private void initializeValidationRules() {
        validationRules.add(this::validatePaymentInstrumentId);
        validationRules.add(this::validatePaymentType);
        validationRules.add(this::validateItems);
        validationRules.add(this::validateDeliveryAddress);
    }

    /**
     * Main validation method
     */
    public ValidationResult validatePurchase(Purchase purchase) {
        Objects.requireNonNull(purchase, "Purchase cannot be null");

        ValidationResult.ValidationResultBuilder builder =
                new ValidationResult.ValidationResultBuilder();

        validationRules.forEach(rule -> rule.validate(purchase, builder));

        return builder.build();
    }

    // Validation rules implementation
    private void validatePaymentInstrumentId(Purchase purchase,
                                             ValidationResult.ValidationResultBuilder builder) {
        Optional.ofNullable(purchase.getPaymentInstrument())
                .map(PaymentInstrument::getId)
                .filter(id -> !id.trim().isEmpty())
                .orElseGet(() -> {
                    builder.addViolation(ValidationConstants.PAYMENT_ID_REQUIRED);
                    return null;
                });
    }

    private void validatePaymentType(Purchase purchase,
                                     ValidationResult.ValidationResultBuilder builder) {
        Optional.ofNullable(purchase.getPaymentInstrument())
                .map(PaymentInstrument::getType)
                .ifPresent(type -> {
                    if (!isValidPaymentType(type)) {
                        builder.addViolation(ValidationConstants.INVALID_PAYMENT_TYPE);
                    }
                });
    }

    private void validateItems(Purchase purchase,
                               ValidationResult.ValidationResultBuilder builder) {
        if (purchase.getItems() == null || purchase.getItems().isEmpty()) {
            builder.addViolation(ValidationConstants.ITEMS_REQUIRED);
        }
    }

    private void validateDeliveryAddress(Purchase purchase,
                                         ValidationResult.ValidationResultBuilder builder) {
        if (purchase.getDeliveryAddressId() == null ||
                purchase.getDeliveryAddressId().trim().isEmpty()) {
            builder.addViolation(ValidationConstants.ADDRESS_REQUIRED);
        }
    }

    private boolean isValidPaymentType(String type) {
        return ValidationConstants.CREDIT_CARD.equals(type) ||
                ValidationConstants.GIFT_CARD.equals(type);
    }

    /**
     * Validation Rule interface
     */
    @FunctionalInterface
    private interface ValidationRule {
        void validate(Purchase purchase, ValidationResult.ValidationResultBuilder builder);
    }

    // Constants for validation
    private static final class ValidationConstants {
        static final String PAYMENT_ID_REQUIRED = "Payment instrument ID must be set";
        static final String INVALID_PAYMENT_TYPE = "Invalid payment instrument type";
        static final String ITEMS_REQUIRED = "Purchase must contain at least one item";
        static final String ADDRESS_REQUIRED = "Delivery address ID must be set";

        static final String CREDIT_CARD = "CreditCard";
        static final String GIFT_CARD = "GiftCard";

        private ValidationConstants() {
        }
    }

    /**
     * Validation Result class with Builder pattern
     */
    public static class ValidationResult {
        private final List<String> violations;
        private final boolean isValid;
        private final Date validationTime;

        private ValidationResult(ValidationResultBuilder builder) {
            this.violations = Collections.unmodifiableList(new ArrayList<>(builder.violations));
            this.isValid = builder.violations.isEmpty();
            this.validationTime = new Date();
        }

        public List<String> getViolations() {
            return violations;
        }

        public boolean isValid() {
            return isValid;
        }

        public Date getValidationTime() {
            return validationTime;
        }

        public static class ValidationResultBuilder {
            private final List<String> violations = new ArrayList<>();

            public ValidationResultBuilder addViolation(String violation) {
                violations.add(violation);
                return this;
            }

            public ValidationResult build() {
                return new ValidationResult(this);
            }
        }
    }
}

/**
 * Test Suite
 */
class PurchaseValidatorTestSuite {
    private static final PurchaseValidator validator = new PurchaseValidator();

    static void runAllTests() {
        System.out.println("Starting Purchase Validator Test Suite");
        System.out.println("=====================================");

        testValidPurchase();
        testInvalidPaymentInstrument();
        testInvalidPaymentType();
        testEmptyItems();
        testMissingAddress();
        testNullPurchase();
        testLargeItemList();

        System.out.println("=====================================");
        System.out.println("Test Suite Completed");
    }

    private static void testValidPurchase() {
        runTest("Valid Purchase Test", createValidPurchase());
    }

    private static void testInvalidPaymentInstrument() {
        Purchase purchase = createValidPurchase();
        purchase.setPaymentInstrument(null);
        runTest("Missing Payment Test", purchase);
    }

    private static void testInvalidPaymentType() {
        Purchase purchase = createValidPurchase();
        purchase.getPaymentInstrument().setType("Invalid");
        runTest("Invalid Payment Type Test", purchase);
    }

    private static void testEmptyItems() {
        Purchase purchase = createValidPurchase();
        purchase.setItems(new ArrayList<>());
        runTest("Empty Items Test", purchase);
    }

    private static void testMissingAddress() {
        Purchase purchase = createValidPurchase();
        purchase.setDeliveryAddressId(null);
        runTest("Missing Address Test", purchase);
    }

    private static void testNullPurchase() {
        try {
            validator.validatePurchase(null);
            System.out.println("Null Purchase Test: FAIL - Should throw exception");
        } catch (NullPointerException e) {
            System.out.println("Null Purchase Test: PASS");
        }
    }

    private static void testLargeItemList() {
        Purchase purchase = createValidPurchase();
        List<Item> largeItemList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeItemList.add(createTestItem("ITEM" + i));
        }
        purchase.setItems(largeItemList);
        runTest("Large Item List Test", purchase);
    }

    private static void runTest(String testName, Purchase purchase) {
        try {
            System.out.println("\nExecuting: " + testName);
            PurchaseValidator.ValidationResult result = validator.validatePurchase(purchase);
            System.out.println("Result: " + (result.isValid() ? "PASS" : "FAIL"));
            if (!result.isValid()) {
                System.out.println("Violations: " +
                        String.join(", ", result.getViolations()));
            }
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
        }
    }

    private static Purchase createValidPurchase() {
        Purchase purchase = new Purchase();
        purchase.setPaymentInstrument(createTestPaymentInstrument());
        purchase.setItems(Collections.singletonList(createTestItem("ITEM1")));
        purchase.setDeliveryAddressId("ADDR123");
        return purchase;
    }

    private static PaymentInstrument createTestPaymentInstrument() {
        PaymentInstrument payment = new PaymentInstrument();
        payment.setId("PAY123");
        payment.setType("CreditCard");
        return payment;
    }

    private static Item createTestItem(String id) {
        Item item = new Item();
        item.setId(id);
        item.setCategory("Electronics");
        item.setCost(99.99);
        return item;
    }
}
