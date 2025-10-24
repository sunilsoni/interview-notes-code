package com.interview.notes.code.year.y2025.october.Amazon.test4;

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
 * Simplified ValidationResult class
 */
class ValidationResult {
    private final List<String> errors;
    private final Date validationTime;

    public ValidationResult() {
        this.errors = new ArrayList<>();
        this.validationTime = new Date();
    }

    public void addError(String error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public Date getValidationTime() {
        return validationTime;
    }
}

/**
 * Simplified Purchase Validator
 */
public class PurchaseValidator {
    private static final String PAYMENT_ID_REQUIRED = "Payment instrument ID must be set";
    private static final String INVALID_PAYMENT_TYPE = "Invalid payment instrument type";
    private static final String ITEMS_REQUIRED = "Purchase must contain at least one item";
    private static final String ADDRESS_REQUIRED = "Delivery address ID must be set";

    private static final Set<String> VALID_PAYMENT_TYPES =
            new HashSet<>(Arrays.asList("CreditCard", "GiftCard"));

    public static void main(String[] args) {
        PurchaseValidatorTestSuite.runAllTests();
    }

    public ValidationResult validatePurchase(Purchase purchase) {
        ValidationResult result = new ValidationResult();

        if (purchase == null) {
            throw new IllegalArgumentException("Purchase cannot be null");
        }

        validatePaymentInstrument(purchase, result);
        validateItems(purchase, result);
        validateDeliveryAddress(purchase, result);

        return result;
    }

    private void validatePaymentInstrument(Purchase purchase, ValidationResult result) {
        PaymentInstrument payment = purchase.getPaymentInstrument();

        if (payment == null || payment.getId() == null || payment.getId().trim().isEmpty()) {
            result.addError(PAYMENT_ID_REQUIRED);
        }

        if (payment != null && payment.getType() != null &&
                !VALID_PAYMENT_TYPES.contains(payment.getType())) {
            result.addError(INVALID_PAYMENT_TYPE);
        }
    }

    private void validateItems(Purchase purchase, ValidationResult result) {
        if (purchase.getItems() == null || purchase.getItems().isEmpty()) {
            result.addError(ITEMS_REQUIRED);
        }
    }

    private void validateDeliveryAddress(Purchase purchase, ValidationResult result) {
        if (purchase.getDeliveryAddressId() == null ||
                purchase.getDeliveryAddressId().trim().isEmpty()) {
            result.addError(ADDRESS_REQUIRED);
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
        } catch (IllegalArgumentException e) {
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
            ValidationResult result = validator.validatePurchase(purchase);
            System.out.println("Result: " + (result.isValid() ? "PASS" : "FAIL"));
            if (!result.isValid()) {
                System.out.println("Errors: " + String.join(", ", result.getErrors()));
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
