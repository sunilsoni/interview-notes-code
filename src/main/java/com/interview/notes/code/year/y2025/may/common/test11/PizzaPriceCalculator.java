package com.interview.notes.code.year.y2025.may.common.test11;

import java.util.ArrayList;
import java.util.List;

/**
 * Pizza Order System
 * Demonstrates OOP concepts with Pizza pricing calculation
 * Author: [Your Name]
 * Version: 1.0
 */

// Base Pizza class with common attributes
class Pizza {
    private String crust;
    private String size;
    private List<String> toppings;
    private double basePrice;

    // Constructor with basic pizza attributes
    public Pizza(String crust, String size) {
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<>();
        this.basePrice = calculateBasePrice();
    }

    // Calculate base price based on size and crust
    private double calculateBasePrice() {
        double price = 0;
        // Base price calculation logic based on size
        switch(size.toLowerCase()) {
            case "small": price = 10.0; break;
            case "medium": price = 12.0; break;
            case "large": price = 14.0; break;
            default: price = 10.0;
        }
        
        // Additional price for crust type
        if(crust.toLowerCase().equals("thick")) {
            price += 2.0;
        }
        return price;
    }

    // Add toppings with price calculation
    public void addTopping(String topping) {
        toppings.add(topping);
    }

    // Calculate final price including toppings
    public double calculateTotalPrice() {
        return basePrice + (toppings.size() * 1.5); // Each topping costs $1.50
    }
}

// Main class for testing
public class PizzaPriceCalculator {
    public static void main(String[] args) {
        // Test Case 1: Small thin crust pizza with 2 toppings
        Pizza pizza1 = new Pizza("thin", "small");
        pizza1.addTopping("cheese");
        pizza1.addTopping("mushroom");
        System.out.println("Test Case 1: $" + pizza1.calculateTotalPrice());
        
        // Test Case 2: Large thick crust pizza with 3 toppings
        Pizza pizza2 = new Pizza("thick", "large");
        pizza2.addTopping("cheese");
        pizza2.addTopping("pepperoni");
        pizza2.addTopping("olives");
        System.out.println("Test Case 2: $" + pizza2.calculateTotalPrice());
        
        // Edge Case: Invalid size with multiple toppings
        Pizza pizza3 = new Pizza("thin", "extra-large");
        pizza3.addTopping("cheese");
        System.out.println("Edge Case: $" + pizza3.calculateTotalPrice());
    }
}
