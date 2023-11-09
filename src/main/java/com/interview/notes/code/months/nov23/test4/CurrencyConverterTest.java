package com.interview.notes.code.months.nov23.test4;

public class CurrencyConverterTest {

    // Helper method to create a CurrencyConverter with default rates for testing
    private CurrencyConverter setupDefaultConverter() {
        return new CurrencyConverter("AUD:USD:0.75,USD:CAD:1.26,JPY:USD:0.129,EUR:GBP:1.156");
    }

    // Test case to check successful conversions
    public void testSuccessfulConversions() {
        CurrencyConverter converter = setupDefaultConverter();
        assert 0.75 == converter.getConversionRate("AUD", "USD") : "AUD to USD conversion failed";
        assert 1.333 == converter.getConversionRate("USD", "AUD") : "USD to AUD conversion failed";
        assert 1.26 == converter.getConversionRate("USD", "CAD") : "USD to CAD conversion failed";
        System.out.println("Test case testSuccessfulConversions passed.");
    }

    // Test case to check conversion of non-existent currency pairs
    public void testNonExistentCurrencyPair() {
        CurrencyConverter converter = setupDefaultConverter();
        try {
            converter.getConversionRate("USD", "INR");
            assert false : "Conversion for non-existent currency pair should throw exception";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("not available") : "Unexpected exception message for non-existent currency pair";
        }
        System.out.println("Test case testNonExistentCurrencyPair passed.");
    }

    // Test case to check handling of null input
    public void testNullInput() {
        try {
            new CurrencyConverter(null);
            assert false : "Constructor with null input should throw exception";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("cannot be null or empty") : "Unexpected exception message for null input";
        }
        System.out.println("Test case testNullInput passed.");
    }

    // Test case to check handling of empty input
    public void testEmptyInput() {
        try {
            new CurrencyConverter("");
            assert false : "Constructor with empty input should throw exception";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("cannot be null or empty") : "Unexpected exception message for empty input";
        }
        System.out.println("Test case testEmptyInput passed.");
    }

    // Test case to check handling of invalid currency codes
    public void testInvalidCurrencyCodes() {
        CurrencyConverter converter = setupDefaultConverter();
        try {
            converter.getConversionRate("XXX", "YYY");
            assert false : "Conversion with invalid currency codes should throw exception";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("not available") : "Unexpected exception message for invalid currency codes";
        }
        System.out.println("Test case testInvalidCurrencyCodes passed.");
    }

    // Main method to run the tests
    public static void main(String[] args) {
        CurrencyConverterTest test = new CurrencyConverterTest();
        test.testSuccessfulConversions();
        test.testNonExistentCurrencyPair();
        test.testNullInput();
        test.testEmptyInput();
        test.testInvalidCurrencyCodes();
    }
}
