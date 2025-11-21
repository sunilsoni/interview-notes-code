package com.interview.notes.code.year.y2024.oct24.amazon.test11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaPriceCalculator {

    // Prices for base bread
    private static final Map<String, Double> BASE_BREAD_PRICES = new HashMap<>();
    // Size multipliers
    private static final Map<String, Double> SIZE_MULTIPLIERS = new HashMap<>();
    // Prices for toppings
    private static final Map<String, Double> TOPPING_PRICES = new HashMap<>();

    static {
        // Initialize base bread prices
        BASE_BREAD_PRICES.put("Regular", 5.00);
        BASE_BREAD_PRICES.put("Gluten Free", 6.00);

        // Initialize size multipliers
        SIZE_MULTIPLIERS.put("Small", 1.0);
        SIZE_MULTIPLIERS.put("Medium", 1.2);
        SIZE_MULTIPLIERS.put("Large", 1.5);
        SIZE_MULTIPLIERS.put("Extra Large", 1.8);

        // Initialize topping prices
        TOPPING_PRICES.put("Pepperoni", 1.50);
        TOPPING_PRICES.put("jalapenos", 1.00);
        TOPPING_PRICES.put("pineapple", 1.00);
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
        Double basePrice = BASE_BREAD_PRICES.get(baseBread);
        Double sizeMultiplier = SIZE_MULTIPLIERS.get(size);

        if (basePrice == null || sizeMultiplier == null) {
            return -1; // Invalid base bread or size
        }

        double toppingsPrice = 0.0;
        for (String topping : toppings) {
            Double toppingPrice = TOPPING_PRICES.get(topping);
            if (toppingPrice != null) {
                toppingsPrice += toppingPrice;
            } else {
                return -1; // Invalid topping
            }
        }

        return (basePrice * sizeMultiplier) + toppingsPrice;
    }

    /**
     * Runs predefined test cases to validate the program.
     */
    public static void runTests() {
        List<TestCase> testCases = Arrays.asList(
                new TestCase("Regular", "Small", List.of("Pepperoni"), 6.50),
                new TestCase("Gluten Free", "Large", Arrays.asList("jalapenos", "pineapple"), 9.70),
                new TestCase("Regular", "Extra Large", Arrays.asList("Pepperoni", "jalapenos", "pineapple"), 12.40),
                new TestCase("InvalidBread", "Small", List.of("Pepperoni"), -1),
                new TestCase("Regular", "InvalidSize", List.of("Pepperoni"), -1),
                new TestCase("Regular", "Small", List.of("InvalidTopping"), -1)
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
