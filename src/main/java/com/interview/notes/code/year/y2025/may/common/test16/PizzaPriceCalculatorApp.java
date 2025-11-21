package com.interview.notes.code.year.y2025.may.common.test16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Enum for pizza sizes with associated base prices
enum PizzaSize {
    SMALL(5.0),    // smallest size costs $5.00
    MEDIUM(7.5),   // medium size costs $7.50
    LARGE(10.0);   // large size costs $10.00

    private final double price;

    PizzaSize(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }  // expose price for calculation
}

// Enum for crust types with associated extra costs
enum CrustType {
    THIN(1.0),     // thin crust adds $1.00
    THICK(2.0),    // thick crust adds $2.00
    CHEESE_STUFFED(3.0); // cheese-stuffed adds $3.00

    private final double price;

    CrustType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }  // expose price for calculation
}

// Enum for toppings with associated costs
enum Topping {
    PEPPERONI(1.5),
    MUSHROOM(1.0),
    ONION(0.75),
    SAUSAGE(1.75),
    BACON(2.0),
    EXTRA_CHEESE(1.25),
    BLACK_OLIVES(1.0);

    private final double price;

    Topping(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }  // expose price for calculation
}

/**
 * @param size     size of pizza
 * @param crust    crust type
 * @param toppings list of toppings
 */ // Domain class representing a pizza order
record Pizza(PizzaSize size, CrustType crust, List<Topping> toppings) {
    // Constructor enforces immutability
    Pizza(PizzaSize size, CrustType crust, List<Topping> toppings) {
        this.size = size;
        this.crust = crust;
        // defensively copy list to prevent external mutation
        this.toppings = new ArrayList<>(toppings);
    }

    @Override
    public List<Topping> toppings() {
        return Collections.unmodifiableList(toppings);
    }
}

// Utility class to calculate pizza prices
class PriceCalculator {
    public static double calculate(Pizza pizza) {
        // Sum size price + crust price + sum of topping prices via Stream
        double sizePrice = pizza.size().getPrice();   // base size cost
        double crustPrice = pizza.crust().getPrice(); // crust extra cost
        double toppingsPrice = pizza.toppings().stream()
                .mapToDouble(Topping::getPrice)               // extract each topping cost
                .sum();                                       // sum all topping costs
        return sizePrice + crustPrice + toppingsPrice;   // total price
    }
}

// Simple class to hold a test case
class TestCase {
    Pizza pizza;        // pizza input
    double expected;    // expected price

    TestCase(Pizza pizza, double expected) {
        this.pizza = pizza;
        this.expected = expected;
    }
}

public class PizzaPriceCalculatorApp {
    public static void main(String[] args) {
        // Prepare test cases
        List<TestCase> tests = Arrays.asList(
                new TestCase(
                        new Pizza(PizzaSize.SMALL, CrustType.THIN,
                                Arrays.asList(Topping.PEPPERONI, Topping.MUSHROOM)),
                        5.0 + 1.0 + 1.5 + 1.0  // SMALL + THIN + PEPPERONI + MUSHROOM
                ),
                new TestCase(
                        new Pizza(PizzaSize.LARGE, CrustType.CHEESE_STUFFED,
                                Collections.emptyList()),
                        10.0 + 3.0             // LARGE + CHEESE_STUFFED, no toppings
                ),
                new TestCase(
                        new Pizza(PizzaSize.MEDIUM, CrustType.THICK,
                                Arrays.asList(Topping.BACON, Topping.EXTRA_CHEESE, Topping.SAUSAGE)),
                        7.5 + 2.0 + 2.0 + 1.25 + 1.75  // MEDIUM + THICK + BACON + EXTRA_CHEESE + SAUSAGE
                )
        );

        // Run small test suite
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            double actual = PriceCalculator.calculate(tc.pizza);
            // Compare with a small epsilon for floating point
            if (Math.abs(actual - tc.expected) < 1e-6) {
                System.out.println("Test " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test " + (i + 1) + ": FAIL (expected "
                        + tc.expected + ", got " + actual + ")");
            }
        }

        // Performance check with large volume
        int largeN = 100_000;                        // number of pizzas
        Pizza sample = new Pizza(                    // sample pizza for repetition
                PizzaSize.LARGE, CrustType.THICK,
                Arrays.asList(Topping.PEPPERONI, Topping.BACON)
        );
        List<Pizza> bulk = Collections.nCopies(largeN, sample);  // list of identical pizzas

        long start = System.currentTimeMillis();     // start timer
        double total = bulk.stream()
                .mapToDouble(PriceCalculator::calculate) // price per pizza
                .sum();                                  // sum all prices
        long duration = System.currentTimeMillis() - start;  // elapsed time

        // Sanity check: total should equal largeN * price(sample)
        double expectedBulk = largeN * PriceCalculator.calculate(sample);
        String result = Math.abs(total - expectedBulk) < 1e-3 ? "PASS" : "FAIL";
        System.out.println("Bulk test (" + largeN + " pizzas): " + result
                + " in " + duration + " ms");
    }
}