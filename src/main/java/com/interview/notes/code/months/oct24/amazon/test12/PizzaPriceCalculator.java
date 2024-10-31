package com.interview.notes.code.months.oct24.amazon.test12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaPriceCalculator {

    // Prices for base bread and size combinations
    private static final Map<String, Double> BASE_PRICES = new HashMap<>();
    // Prices for toppings
    private static final Map<String, Double> TOPPING_PRICES = new HashMap<>();
    // Topping size multipliers
    private static final Map<String, Double> SIZE_MULTIPLIERS = new HashMap<>();

    static {
        // Initialize base prices per base bread and size
        BASE_PRICES.put("Regular_Small", 5.00);
        BASE_PRICES.put("Regular_Medium", 6.50);
        BASE_PRICES.put("Regular_Large", 7.70);
        BASE_PRICES.put("Regular_Extra Large", 8.90);

        BASE_PRICES.put("Gluten Free_Small", 6.00);
        BASE_PRICES.put("Gluten Free_Medium", 7.50);
        BASE_PRICES.put("Gluten Free_Large", 7.70);
        BASE_PRICES.put("Gluten Free_Extra Large", 9.40);

        // Initialize topping prices
        TOPPING_PRICES.put("Pepperoni", 1.50);
        TOPPING_PRICES.put("jalapenos", 1.00);
        TOPPING_PRICES.put("pineapple", 1.00);

        // Initialize size multipliers for toppings
        SIZE_MULTIPLIERS.put("Small", 1.0);
        SIZE_MULTIPLIERS.put("Medium", 1.2);
        SIZE_MULTIPLIERS.put("Large", 1.5);
        SIZE_MULTIPLIERS.put("Extra Large", 1.8);
    }

    public static void main(String[] args) {
        // Run test cases
        runTests();

        // Uncomment below lines to run the program interactively
        /*
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Base Bread (Gluten Free, Regular): ");
        String baseBread = scanner.nextLine();

        System.out.print("Enter Size (Small, Medium, Large, Extra Large): ");
        String size = scanner.nextLine();

        System.out.print("Enter Toppings separated by commas (Pepperoni, jalapenos, pineapple): ");
        String toppingsInput = scanner.nextLine();
        List<String> toppings = Arrays.asList(toppingsInput.split("\\s*,\\s*"));

        double price = calculatePrice(baseBread, size, toppings);
        if (price >= 0) {
            System.out.printf("Total price of the pizza: $%.2f%n", price);
        } else {
            System.out.println("Invalid input provided.");
        }
        */
    }

    /**
     * Calculates the price of the pizza based on base bread, size, and toppings.
     *
     * @param baseBread The type of base bread.
     * @param size      The size of the pizza.
     * @param toppings  The list of toppings.
     * @return The total price of the pizza, or -1 if inputs are invalid.
     */
    public static double calculatePrice(String baseBread, String size, List<String> toppings) {
        String key = baseBread + "_" + size;
        Double basePrice = BASE_PRICES.get(key);
        Double sizeMultiplier = SIZE_MULTIPLIERS.get(size);

        if (basePrice == null || sizeMultiplier == null) {
            return -1; // Invalid base bread or size
        }

        double toppingsPrice = 0.0;
        for (String topping : toppings) {
            Double toppingPrice = TOPPING_PRICES.get(topping);
            if (toppingPrice != null) {
                toppingsPrice += toppingPrice * sizeMultiplier;
            } else {
                return -1; // Invalid topping
            }
        }

        return basePrice + toppingsPrice;
    }

    /**
     * Runs predefined test cases to validate the program.
     */
    public static void runTests() {
        List<TestCase> testCases = Arrays.asList(
                new TestCase("Regular", "Small", Arrays.asList("Pepperoni"), 6.50),
                new TestCase("Gluten Free", "Large", Arrays.asList("jalapenos", "pineapple"), 10.70),
                new TestCase("Regular", "Extra Large", Arrays.asList("Pepperoni", "jalapenos", "pineapple"), 15.20),
                new TestCase("InvalidBread", "Small", Arrays.asList("Pepperoni"), -1),
                new TestCase("Regular", "InvalidSize", Arrays.asList("Pepperoni"), -1),
                new TestCase("Regular", "Small", Arrays.asList("InvalidTopping"), -1)
        );

        int passCount = 0;
        for (TestCase testCase : testCases) {
            double result = calculatePrice(testCase.baseBread, testCase.size, testCase.toppings);
            boolean passed = Math.abs(result - testCase.expectedPrice) < 0.01;
            if (passed) {
                passCount++;
                System.out.println("Test case PASSED");
            } else {
                System.out.println("Test case FAILED");
                System.out.printf("Expected: $%.2f, Got: $%.2f%n", testCase.expectedPrice, result);
            }
        }
        System.out.printf("Passed %d out of %d test cases.%n", passCount, testCases.size());

        // Testing with large data input
        largeDataTest();
    }

    /**
     * Simulates a large number of orders to test performance.
     */
    public static void largeDataTest() {
        int largeNumber = 100000;
        boolean passed = true;
        for (int i = 0; i < largeNumber; i++) {
            double price = calculatePrice("Regular", "Medium", Arrays.asList("Pepperoni", "jalapenos"));
            if (price < 0) {
                passed = false;
                break;
            }
        }
        if (passed) {
            System.out.println("Large data input test PASSED");
        } else {
            System.out.println("Large data input test FAILED");
        }
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        String baseBread;
        String size;
        List<String> toppings;
        double expectedPrice;

        TestCase(String baseBread, String size, List<String> toppings, double expectedPrice) {
            this.baseBread = baseBread;
            this.size = size;
            this.toppings = toppings;
            this.expectedPrice = expectedPrice;
        }
    }
}
