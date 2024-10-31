package com.interview.notes.code.months.oct24.amazon.test12;

import java.util.*;

/**
 * PizzaPriceCalculator is a program that calculates the price of a pizza based on user-selected
 * base bread type, size, and toppings. It includes methods to process inputs, calculate prices,
 * and perform test cases to ensure accuracy.
 */
public class PizzaPriceCalculator1 {

    // Maps to store prices for bread types, sizes, and toppings.
    private static final Map<String, Double> breadPrices = new HashMap<>();
    private static final Map<String, Double> sizePrices = new HashMap<>();
    private static final Map<String, Double> toppingPrices = new HashMap<>();

    // Static block to initialize price maps
    static {
        // Initialize bread prices
        breadPrices.put("Gluten Free", 2.0);
        breadPrices.put("Regular", 0.0);

        // Initialize size prices
        sizePrices.put("Small", 8.0);
        sizePrices.put("Medium", 10.0);
        sizePrices.put("Large", 12.0);
        sizePrices.put("Extra Large", 14.0);

        // Initialize topping prices
        toppingPrices.put("Pepperoni", 1.5);
        toppingPrices.put("Jalapenos", 1.0);
        toppingPrices.put("Pineapple", 1.2);
    }

    /**
     * Main method to execute the pizza price calculator and run test cases.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Uncomment the following line to enable user input functionality
        // runPizzaCalculator();

        // Run all test cases
        runTests();
    }

    /**
     * Prompts the user for input and calculates the total price of the pizza.
     * This method is not invoked in the main method by default.
     */
    private static void runPizzaCalculator() {
        Scanner scanner = new Scanner(System.in);

        // Get Base Bread
        String bread = getUserChoice(scanner, "Choose Base Bread (Gluten Free, Regular): ", breadPrices.keySet());

        // Get Size
        String size = getUserChoice(scanner, "Choose Size (Small, Medium, Large, Extra Large): ", sizePrices.keySet());

        // Get Toppings
        List<String> toppings = getUserToppings(scanner, "Choose Toppings (Pepperoni, Jalapenos, Pineapple). Enter 'done' when finished:");

        // Calculate total price
        double totalPrice = calculateTotalPrice(bread, size, toppings);

        // Display the result
        System.out.printf("Your pizza total is: $%.2f%n", totalPrice);
        scanner.close();
    }

    /**
     * Prompts the user to select a choice from the provided options.
     *
     * @param scanner Scanner object for input
     * @param prompt  Prompt message
     * @param options Set of valid options
     * @return Selected option as a String
     */
    private static String getUserChoice(Scanner scanner, String prompt, Set<String> options) {
        String choice;
        while (true) {
            System.out.print(prompt);
            choice = scanner.nextLine().trim();
            if (options.contains(choice)) {
                break;
            } else {
                System.out.println("Invalid choice. Please select from the provided options.");
            }
        }
        return choice;
    }

    /**
     * Prompts the user to select multiple toppings.
     *
     * @param scanner Scanner object for input
     * @param prompt  Prompt message
     * @return List of selected toppings
     */
    private static List<String> getUserToppings(Scanner scanner, String prompt) {
        List<String> selectedToppings = new ArrayList<>();
        System.out.println(prompt);
        while (true) {
            System.out.print("Add Topping: ");
            String topping = scanner.nextLine().trim();
            if (topping.equalsIgnoreCase("done")) {
                break;
            }
            if (toppingPrices.containsKey(topping)) {
                selectedToppings.add(topping);
                System.out.println(topping + " added.");
            } else {
                System.out.println("Invalid topping. Please select from the available toppings.");
            }
        }
        return selectedToppings;
    }

    /**
     * Calculates the total price of the pizza based on selected options.
     *
     * @param bread    Selected bread type
     * @param size     Selected size
     * @param toppings List of selected toppings
     * @return Total price as a double
     */
    public static double calculateTotalPrice(String bread, String size, List<String> toppings) {
        double total = 0.0;
        total += breadPrices.get(bread);
        total += sizePrices.get(size);
        for (String topping : toppings) {
            total += toppingPrices.get(topping);
        }
        return total;
    }

    /**
     * Runs predefined test cases to validate the pizza price calculator.
     */
    private static void runTests() {
        List<TestCase> testCases = new ArrayList<>();

        // Define test cases
        testCases.add(new TestCase("Regular", "Small", Arrays.asList(), 8.0));
        testCases.add(new TestCase("Gluten Free", "Medium", Arrays.asList("Pepperoni"), 10.0 + 2.0 + 1.5));
        testCases.add(new TestCase("Regular", "Large", Arrays.asList("Jalapenos", "Pineapple"), 12.0 + 0.0 + 1.0 + 1.2));
        testCases.add(new TestCase("Gluten Free", "Extra Large", Arrays.asList("Pepperoni", "Jalapenos", "Pineapple"), 14.0 + 2.0 + 1.5 + 1.0 + 1.2));
        testCases.add(new TestCase("Regular", "Medium", Arrays.asList("Pepperoni", "Pepperoni", "Pineapple"), 10.0 + 0.0 + 1.5 + 1.5 + 1.2));
        testCases.add(new TestCase("Gluten Free", "Small", Arrays.asList("Jalapenos"), 8.0 + 2.0 + 1.0));
        testCases.add(new TestCase("Regular", "Extra Large", Arrays.asList("Pineapple", "Pineapple", "Pineapple"), 14.0 + 0.0 + 1.2 + 1.2 + 1.2));
        testCases.add(new TestCase("Regular", "InvalidSize", Arrays.asList("Pepperoni"), -1.0)); // Invalid size
        testCases.add(new TestCase("InvalidBread", "Medium", Arrays.asList("Pepperoni"), -1.0)); // Invalid bread
        testCases.add(new TestCase("Regular", "Large", Arrays.asList("InvalidTopping"), -1.0)); // Invalid topping

        // Execute test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            double calculatedPrice = -1.0;
            boolean isValid = true;

            if (!breadPrices.containsKey(tc.bread)) {
                isValid = false;
            }
            if (!sizePrices.containsKey(tc.size)) {
                isValid = false;
            }
            for (String topping : tc.toppings) {
                if (!toppingPrices.containsKey(topping)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                calculatedPrice = calculateTotalPrice(tc.bread, tc.size, tc.toppings);
            }

            boolean pass;
            if (tc.expectedPrice < 0) {
                pass = !isValid;
            } else {
                pass = Math.abs(calculatedPrice - tc.expectedPrice) < 0.0001;
            }

            if (pass) {
                System.out.printf("Test Case %d: PASS%n", i + 1);
                passed++;
            } else {
                System.out.printf("Test Case %d: FAIL (Expected: %.2f, Got: %.2f)%n", i + 1, tc.expectedPrice, calculatedPrice);
            }
        }

        System.out.printf("Passed %d/%d test cases.%n", passed, testCases.size());

        // Additional test for large data inputs (e.g., maximum number of toppings)
        runLargeDataTest();
    }

    /**
     * Runs a test case with a large number of toppings to simulate large data input.
     */
    private static void runLargeDataTest() {
        // Assuming the maximum toppings are 1000 of each available topping
        List<String> largeToppings = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeToppings.add("Pepperoni");
            largeToppings.add("Jalapenos");
            largeToppings.add("Pineapple");
        }

        double expectedPrice = breadPrices.get("Regular") + sizePrices.get("Extra Large")
                + (1000 * toppingPrices.get("Pepperoni"))
                + (1000 * toppingPrices.get("Jalapenos"))
                + (1000 * toppingPrices.get("Pineapple"));

        double calculatedPrice = calculateTotalPrice("Regular", "Extra Large", largeToppings);

        if (Math.abs(calculatedPrice - expectedPrice) < 0.0001) {
            System.out.println("Large Data Test: PASS");
        } else {
            System.out.printf("Large Data Test: FAIL (Expected: %.2f, Got: %.2f)%n", expectedPrice, calculatedPrice);
        }
    }

    /**
     * TestCase class represents a single test case with input parameters and the expected output.
     */
    static class TestCase {
        String bread;
        String size;
        List<String> toppings;
        double expectedPrice;

        TestCase(String bread, String size, List<String> toppings, double expectedPrice) {
            this.bread = bread;
            this.size = size;
            this.toppings = toppings;
            this.expectedPrice = expectedPrice;
        }
    }
}