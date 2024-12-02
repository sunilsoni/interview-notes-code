package com.interview.notes.code.year.y2024.sept24.amazon.test4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaPriceCalculator {
    private static final Map<String, Double> TOPPINGS_PRICES = new HashMap<>();

    static {
        TOPPINGS_PRICES.put("cheese", 2.0);
        TOPPINGS_PRICES.put("mushrooms", 1.5);
        TOPPINGS_PRICES.put("olives", 1.0);
    }

    public static double calculatePizzaPrice(Base base, Size size, List<String> toppings) {
        double basePrice = base.getPrice();
        double toppingsPrice = toppings.stream()
                .mapToDouble(topping -> TOPPINGS_PRICES.getOrDefault(topping, 0.0))
                .sum();
        return (basePrice + toppingsPrice) * size.getMultiplier();
    }

    public static void main(String[] args) {
        List<String> toppings1 = Arrays.asList("cheese");
        double price1 = calculatePizzaPrice(Base.THIN, Size.SMALL, toppings1);
        System.out.println("Test Case 1: " + (price1 == 7.50 ? "Pass" : "Fail"));

        List<String> toppings2 = Arrays.asList("cheese");
        double price2 = calculatePizzaPrice(Base.THIN, Size.MEDIUM, toppings2);
        System.out.println("Test Case 2: " + (price2 == 10.00 ? "Pass" : "Fail"));

        List<String> toppings3 = Arrays.asList("cheese", "mushrooms");
        double price3 = calculatePizzaPrice(Base.REGULAR, Size.LARGE, toppings3);
        System.out.println("Test Case 3: " + (price3 == 16.875 ? "Pass" : "Fail"));

        List<String> toppings4 = Arrays.asList("olives", "mushrooms");
        double price4 = calculatePizzaPrice(Base.CHEESY_CRUST, Size.SMALL, toppings4);
        System.out.println("Test Case 4: " + (price4 == 10.875 ? "Pass" : "Fail"));
    }

    enum Base {
        THIN(8), REGULAR(10), CHEESY_CRUST(12);
        private final double price;

        Base(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }

    enum Size {
        SMALL(0.75), MEDIUM(1.0), LARGE(1.25);
        private final double multiplier;

        Size(double multiplier) {
            this.multiplier = multiplier;
        }

        public double getMultiplier() {
            return multiplier;
        }
    }
}
