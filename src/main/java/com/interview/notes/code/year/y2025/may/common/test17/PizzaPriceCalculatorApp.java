package com.interview.notes.code.year.y2025.may.common.test17;

import java.util.*;
import java.util.stream.Collectors;

// --- Pricing Enums ---

// Pizza sizes with base prices
enum PizzaSize {
    SMALL(5.0),    // $5.00 base
    MEDIUM(7.5),   // $7.50 base
    LARGE(10.0);   // $10.00 base

    private final double price;

    PizzaSize(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }  // expose for calculator
}

// Crust types with extra costs
enum CrustType {
    THIN(1.0),         // +$1.00
    THICK(2.0),        // +$2.00
    CHEESE_STUFFED(3.0);// +$3.00

    private final double price;

    CrustType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }  // expose for calculator
}

// Toppings with per-item costs
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
    }  // expose for calculator
}

// --- Domain Model ---

// Offer interface: calculate discount or surcharge
interface Offer {
    /**
     * @param pizzas   list of pizzas in the order
     * @param subtotal base total before any offers
     * @return adjustment amount (negative = discount)
     */
    double apply(List<Pizza> pizzas, double subtotal);
}

// --- Core Price Calculator ---

// Immutable Pizza order
record Pizza(PizzaSize size, CrustType crust, List<Topping> toppings) {
    Pizza(PizzaSize size, CrustType crust, List<Topping> toppings) {
        this.size = size;
        this.crust = crust;
        // defensive copy to preserve immutability
        this.toppings = new ArrayList<>(toppings);
    }

    @Override
    public List<Topping> toppings() {
        return Collections.unmodifiableList(toppings);
    }
}

// --- Offer Strategy Pattern ---

class PriceCalculator {
    public static double calculate(Pizza pizza) {
        // base size cost
        double sizePrice = pizza.size().getPrice();
        // crust cost
        double crustPrice = pizza.crust().getPrice();
        // sum of topping costs
        double toppingsPrice = pizza.toppings().stream()
                .mapToDouble(Topping::getPrice)
                .sum();
        // total pizza price
        return sizePrice + crustPrice + toppingsPrice;
    }
}

// Buy-One-Get-One-Free offer on identical pizzas
class BogoOffer implements Offer {
    @Override
    public double apply(List<Pizza> pizzas, double subtotal) {
        // group by identical pizza, count occurrences
        Map<Pizza, Long> counts = pizzas.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        // for each group, one free per pair => discount = price * (count/2)
        double discount = counts.entrySet().stream()
                .filter(e -> e.getValue() >= 2)                  // at least a pair
                .mapToDouble(e -> {
                    long pairs = e.getValue() / 2;                // integer division
                    double unitPrice = PriceCalculator.calculate(e.getKey());
                    return unitPrice * pairs;                    // one free per pair
                })
                .sum();
        return -discount;  // negative => reduce total
    }
}

// --- Checkout Utility ---

class Checkout {
    /**
     * Calculate the final total after applying all offers.
     */
    public static double calculateTotal(List<Pizza> pizzas, List<Offer> offers) {
        // 1. compute base subtotal
        double subtotal = pizzas.stream()
                .mapToDouble(PriceCalculator::calculate)
                .sum();
        // 2. apply each offer in sequence
        double adjustment = offers.stream()
                .mapToDouble(o -> o.apply(pizzas, subtotal))
                .sum();
        // 3. return adjusted total
        return subtotal + adjustment;
    }
}

// --- Application Entry Point ---

public class PizzaPriceCalculatorApp {
    public static void main(String[] args) {
        // Sample order of pizzas
        List<Pizza> order = Arrays.asList(
                new Pizza(PizzaSize.MEDIUM, CrustType.THICK,
                        Arrays.asList(Topping.PEPPERONI, Topping.MUSHROOM)),
                new Pizza(PizzaSize.MEDIUM, CrustType.THICK,
                        Arrays.asList(Topping.PEPPERONI, Topping.MUSHROOM)),
                new Pizza(PizzaSize.LARGE, CrustType.THIN,
                        List.of(Topping.BACON))
        );

        // Active offers: e.g., buy-one-get-one-free
        List<Offer> activeOffers = List.of(
                new BogoOffer()
                // you can add more offers here
        );

        // Calculate final total
        double totalCost = Checkout.calculateTotal(order, activeOffers);

        // Print breakdown
        System.out.println("Order summary:");
        order.forEach(p -> System.out.println("  - " +
                p.size() + "/" + p.crust() +
                " w/ " + p.toppings()));
        System.out.printf("Final total (after offers): $%.2f%n", totalCost);
    }
}